package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public interface PostRepository {

  final HashMap<Long, Post> posts = new HashMap<>();
  final AtomicLong idPost = new AtomicLong();//для многопоточки

  public default List<Post> all() {
      return new ArrayList<>(posts.values());
    //return Collections.emptyList();
  }

  public default Optional<Post> getById(long id) {
    return Optional.ofNullable(posts.get(id)); // ofNullable - чтобы не выбрасывалось исключение
   // return Optional.empty();
  }

  public default Post save(Post post) {
if (post.getId() == 0) {
  post.setId(idPost.getAndIncrement());
  posts.put(idPost.getAndIncrement(), post);
  return post;
  }
  if (post.getId() != 0 && posts.containsKey(post.getId())){
    posts.put(post.getId(), post);
    return post;
  }
    return post;
  }

  public default void removeById(long id) {
posts.remove(id);
  }
}
