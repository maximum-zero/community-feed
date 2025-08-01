package org.maximum0.post.repository.entity.post;

import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.maximum0.common.domain.PositiveIntegerCounter;
import org.maximum0.common.repository.entity.TimeBaseEntity;
import org.maximum0.post.domain.Post;
import org.maximum0.post.domain.content.PostContent;
import org.maximum0.post.domain.content.PostPublicationState;
import org.maximum0.user.repository.entity.UserEntity;

@Entity
@Table(name = "tb_post")
@NoArgsConstructor
@Getter
public class PostEntity extends TimeBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UserEntity author;

    private String content;

    @Convert(converter = PostPublicationStateConverter.class)
    private PostPublicationState state;

    private Integer likeCount;

    public PostEntity(Post post) {
        this.id = post.getId();
        this.author = new UserEntity(post.getAuthor());
        this.content = post.getContent();
        this.state = post.getState();
        this.likeCount = post.getLikeCount();
    }

    public Post toPost() {
        return Post.builder()
                .id(id)
                .author(author.toUser())
                .content(new PostContent(content))
                .state(state)
                .likeCounter(new PositiveIntegerCounter(likeCount))
                .build();
    }

}
