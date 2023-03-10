package org.example.model;

import com.google.gson.annotations.Expose;

import java.util.Objects;

public class Post {
    @Expose(serialize = true ) private long id;
    @Expose(serialize = true )  private String content;
    private PostStatus postStatus = PostStatus.POSTED;

    public Post() {
    }


    public Post(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public PostStatus getPostStatus() {
        return postStatus;
    }
    public void setPostedStatus() {
        postStatus = PostStatus.POSTED;
    }
    public void setRemovedStatus() {
        postStatus = PostStatus.REMOVED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id && Objects.equals(content, post.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }


}