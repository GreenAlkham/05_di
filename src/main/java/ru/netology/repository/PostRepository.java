package ru.netology.repository;

import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

// Stub
public class PostRepository {

  private final HashMap<Long, Post> posts = new HashMap<>();
  private final AtomicLong idPost = new AtomicLong();//для многопоточки

  public List<Post> all() {
      return new ArrayList<>(posts.values());
    //return Collections.emptyList();
  }

  public Optional<Post> getById(long id) {
    return Optional.ofNullable(posts.get(id)); // ofNullable - чтобы не выбрасывалось исключение
   // return Optional.empty();
  }

  public Post save(Post post) {
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

  public void removeById(long id) {
posts.remove(id);
  }
}
