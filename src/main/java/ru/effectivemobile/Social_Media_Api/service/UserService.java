package ru.effectivemobile.Social_Media_Api.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.effectivemobile.Social_Media_Api.entity.User;
import ru.effectivemobile.Social_Media_Api.exception.ElemNotFoundChecked;
import ru.effectivemobile.Social_Media_Api.exception.IFElementExist;
import ru.effectivemobile.Social_Media_Api.loger.FormLogInfo;
import ru.effectivemobile.Social_Media_Api.repository.UserRepository;

import java.util.Optional;
import java.util.Set;

/**
 * Сервис для поользователей
 */

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserService {
    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void getRequestFriend(long friendId, Authentication authentication) {
        User user = getUser(authentication);
        if (user.getUserFriends().contains(friendId))
            throw new IFElementExist("У вас уже есть такой друг с id: " + friendId);
        Set<Long> setSubscribed = user.getUserSubscribed();
        if(!setSubscribed.contains(friendId)){
            setSubscribed.add(friendId);
            user.setUserSubscribed(setSubscribed);
            userRepository.save(user);
        }

        log.info(FormLogInfo.getInfo("Создаем реквест другу"));
        User requestUser = getUserById(friendId);
        Set<Long> setRequest = requestUser.getUserRequests();
        setRequest.add(user.getUserId());
        requestUser.setUserRequests(setRequest);
    }

    public void refuseOrAcceptRequest(long idUser, boolean trueOrFalse, Authentication authentication) {
        User user = getUser(authentication);
        if (!user.getUserRequests().contains(idUser))
            throw new ElemNotFoundChecked("У вас нет такого запроса в друзья по id: " + idUser);
        if (trueOrFalse) {
            log.info(FormLogInfo.getInfo("Принимаем в дружбу"));
            Set<Long> setRequest = user.getUserRequests();
            Set<Long> setSubscribed = user.getUserSubscribed();
            Set<Long> setFriend = user.getUserFriends();
            setRequest.remove(idUser);
            setSubscribed.add(idUser);
            setFriend.add(idUser);
            user.setUserRequests(setRequest);
            user.setUserSubscribed(setSubscribed);
            user.setUserFriends(setFriend);
            userRepository.save(user);


            User userFriend = getUserById(idUser);
            Set<Long> setFriend1 = user.getUserFriends();
            setFriend1.add(user.getUserId());
            userFriend.setUserFriends(setFriend1);
            userRepository.save(userFriend);

        }else {
            log.info(FormLogInfo.getInfo("Отказываем в дружбе"));
            Set<Long> setRequest = user.getUserRequests();
            setRequest.remove(idUser);
            user.setUserRequests(setRequest);
            userRepository.save(user);
        }
    }

    public void deleteFriend(long idFriend,Authentication authentication){
        User user = getUser(authentication);
        if (!user.getUserFriends().contains(idFriend))
            throw new IFElementExist("У вас нет друга с id: " + idFriend);
        log.info(FormLogInfo.getInfo("Удаляем друга"));
        Set<Long> setSubscribed = user.getUserSubscribed();
        Set<Long> setFriend = user.getUserFriends();
        setFriend.remove(idFriend);
        setSubscribed.remove(idFriend);
        user.setUserSubscribed(setSubscribed);
        user.setUserFriends(setFriend);
        userRepository.save(user);

        User other = getUserById(idFriend);
        Set<Long> setFriend1 = other.getUserFriends();
        setFriend1.remove(user.getUserId());
        other.setUserFriends(setFriend);
        userRepository.save(other);

    }

    private User getUser(Authentication authentication) {
        log.info(FormLogInfo.getInfo("Получаем данные пользователя"));
        Optional<User> user = userRepository.findByUserEmail(authentication.getName());
        if (user.isEmpty()) throw new ElemNotFoundChecked("Такого юзера нет");
        return user.get();
    }

    private User getUserById(long id) {
        log.info(FormLogInfo.getInfo("Получаем данные пользователя"));
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) throw new ElemNotFoundChecked("Такого юзера нет");
        return user.get();
    }

}
