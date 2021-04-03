package com.resumly.resumeapi.modules.user.services;

import com.resumly.resumeapi.core.domain.GlobalMessage;
import com.resumly.resumeapi.core.domain.Message;
import com.resumly.resumeapi.core.exceptions.EmailAlreadyUsedException;
import com.resumly.resumeapi.core.exceptions.InvalidPasswordException;
import com.resumly.resumeapi.core.exceptions.LoginAlreadyUsedException;
import com.resumly.resumeapi.modules.core.exceptions.CustomException;
import com.resumly.resumeapi.modules.core.exceptions.DataValidationException;
import com.resumly.resumeapi.modules.core.services.BaseService;
import com.resumly.resumeapi.modules.user.domain.Authority;
import com.resumly.resumeapi.modules.user.domain.User;
import com.resumly.resumeapi.modules.user.dto.UserDTO;
import com.resumly.resumeapi.modules.user.repository.UserRepository;
import com.resumly.resumeapi.modules.user.rest.vm.LoginVM;
import com.resumly.resumeapi.modules.user.services.mappers.UserMapper;
import com.resumly.resumeapi.modules.user.services.utils.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService extends BaseService implements IUserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final CacheManager cacheManager;
    private final UserMapper userMapper;


    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, CacheManager cacheManager, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.cacheManager = cacheManager;
        this.userMapper = userMapper;
    }

    private void clearUserCaches(User user) {
        Objects.requireNonNull(cacheManager.getCache(UserRepository.USERS_BY_LOGIN_CACHE)).evict(user.getLogin());
        Objects.requireNonNull(cacheManager.getCache(UserRepository.USERS_BY_EMAIL_CACHE)).evict(user.getEmail());
    }

    @Override
    public UserDTO authenticateUser(LoginVM loginVM) throws CustomException {
        Optional<User> optionalUser = userRepository.findByEmail(loginVM.getUsername());
        validateOptionalEntity(optionalUser);
        if (!passwordEncoder.matches(loginVM.getPassword(), optionalUser.get().getPassword()))
            throw new InvalidPasswordException();

        UserDTO userDTO = userMapper.toDTO(optionalUser.get());

        return userDTO;
    }

    @Override
    public UserDTO registerUser(UserDTO userDTO, String password) throws CustomException {
        Optional<User> loginUser = userRepository.findOneByLogin(userDTO.getLogin().toLowerCase());
        if (loginUser.isPresent()) throw new LoginAlreadyUsedException();

        Optional<User> emailUser =  userRepository.findByEmail(userDTO.getEmail());
        if (emailUser.isPresent()) throw new EmailAlreadyUsedException();


        User newUser = userMapper.toEntity(userDTO);
        String encryptedPassword = passwordEncoder.encode(password);
        newUser.setPassword(encryptedPassword);
        newUser.setActivationKey(RandomUtil.generateActivationKey());
        User user = userRepository.save(newUser);

        return userMapper.toDTO(user);
    }

    @Override
    public void updateUser(UserDTO userDTO) throws CustomException {
        Optional<UserDTO> optionalUserDTO = Optional.of(userRepository
                .findById(userDTO.getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(user -> {
                    this.clearUserCaches(user);
                    UserMapper.baseMapingUserDTOToUser(userDTO, user);
                    userRepository.save(user);
                    this.clearUserCaches(user);
                    log.debug("Changed Information for User: {}", user);
                    return user;
                })
                .map(userMapper::toDTO);

        if (optionalUserDTO.isEmpty()) throw new DataValidationException(new GlobalMessage("Error Updating User", "Not Found", Message.Severity.ERROR));
    }

}
