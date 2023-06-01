package ru.effectivemobile.Social_Media_Api.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.effectivemobile.Social_Media_Api.entity.Post;
import ru.effectivemobile.Social_Media_Api.entity.User;
import ru.effectivemobile.Social_Media_Api.exception.ElemNotFoundChecked;
import ru.effectivemobile.Social_Media_Api.exception.Unauthorized;
import ru.effectivemobile.Social_Media_Api.loger.FormLogInfo;
import ru.effectivemobile.Social_Media_Api.repository.PostRepository;
import ru.effectivemobile.Social_Media_Api.repository.UserRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE_NEW;


/**
 * Cервис для управления постами
 */

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class PostService {

    final PostRepository postRepository;
    final UserRepository userRepository;
    @Value("${image.post.dir.path}")
    String imagePostDir;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public void createNewPost(String text, String title, MultipartFile multipartFile, Authentication authentication) {
        int count = postRepository.findMaxID();
        int countReal = count + 1;
        Path filePath = getPath(imagePostDir, countReal);
        String linkToGetImage = getLinkToGetImage(countReal);

        try {
            Files.createDirectories(filePath.getParent());
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (InputStream is = multipartFile.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
            log.info(FormLogInfo.getInfo("Сохраняем картинку поста в корневой папке:\n " + linkToGetImage));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        log.info(FormLogInfo.getInfo("Создается новый пост"));
        User user;
        user = getUser(authentication);

        Post newPost = new Post();
        newPost.setTitle(title);
        newPost.setText(text);
        newPost.setPath(linkToGetImage);

        List<Post> postList = user.getUserPosts();
        postList.add(newPost);
        user.setUserPosts(postList);
        userRepository.save(user);

        log.info(FormLogInfo.getInfo("Пост успешно сохранен "));
    }

    public List<Post> getAnotherPostById(long id) {
        log.info(FormLogInfo.getInfo("Получаем посты по id"));
        return getUserById(id).getUserPosts();
    }

    public void deletePost(long id, Authentication authentication) {
        try {
            if (checkAuth(id, authentication)) {
                log.info(FormLogInfo.getInfo("Удаляем пост по id"));
                Post post = getPostById(id);
                Files.deleteIfExists(Path.of(post.getPath()));
                postRepository.deleteById(id);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePost(long id, Post post, Authentication authentication) {
        if (checkAuth(id, authentication)) {
            log.info(FormLogInfo.getInfo("Обноваляем пост по id. Только данные, без картинки"));
            Post newPost = getPostById(id);
            newPost.setPostId(post.getPostId());
            newPost.setUserPosts(post.getUserPosts());
            newPost.setTitle(post.getTitle());
            newPost.setText(post.getText());
            newPost.setCreatedAt(LocalDateTime.now());
            postRepository.save(newPost);
        }
    }

    public void updatePostImage(long id, MultipartFile multipartFile, Authentication authentication) {
        try {
            if (checkAuth(id, authentication)) {
                log.info(FormLogInfo.getInfo("Обноваляем пост по id. Только картинка"));
                Post post = getPostById(id);
                Files.deleteIfExists(Path.of(post.getPath()));

                try (InputStream is = multipartFile.getInputStream();
                     OutputStream os = Files.newOutputStream(Path.of(post.getPath()), CREATE_NEW);
                     BufferedInputStream bis = new BufferedInputStream(is, 1024);
                     BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
                ) {
                    bis.transferTo(bos);
                    log.info(FormLogInfo.getInfo("Сохраняем картинку поста в корневой папке:\n " + post.getPath()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                post.setPath(post.getPath());
                post.setCreatedAt(LocalDateTime.now());
                postRepository.save(post);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    private Post getPostById(long id){
        log.info(FormLogInfo.getInfo("Получаем данные поста"));
        Optional<Post> post = postRepository.findById(id);
        if (post.isEmpty()) throw new ElemNotFoundChecked("Такого юзера нет");
        return post.get();
    }

    private boolean checkAuth(long id, Authentication authentication){
        log.info(FormLogInfo.getInfo("Проверка авторизации"));
        Post post;
        post = getPostById(id);
        User user = post.getUserPosts();
        User userCheck;
        userCheck = getUser(authentication);
        if (!Objects.equals(user.getUserId(), userCheck.getUserId())) throw new Unauthorized();
        return true;
    }

    private Path getPath(String nameDir, Integer id) {
        return Path.of(nameDir,
                Objects.requireNonNull(String.valueOf(id)));
    }

    private String getLinkToGetImage(Integer id) {
        return "/post" + "/" + id;
    }


}
