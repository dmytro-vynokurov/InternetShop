package entities;

import javax.persistence.*;
import java.sql.Date;

/**
 * User: Dmytro_Vynokurov
 * Date: 6/20/13
 * Time: 5:19 PM
 */
@Entity
@Table(name = "\"COMMENT\"", catalog = "", schema = "INTERNETSHOP")
public final class Comment {
    @Column(name = "ID_COMMENT", nullable = false, insertable = true, updatable = true, length = 6, precision = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_Comment")
    @SequenceGenerator(name = "S_Comment", sequenceName = "S_COMMENT", allocationSize = 1)
    private int idComment;
    @Column(name = "DATE_PUBLISHED", nullable = false, insertable = true, updatable = true, length = 7, precision = 0)
    @Basic
    private Date datePublished;
    @Column(name = "COMMENT_TEXT", nullable = false, insertable = true, updatable = true, length = 1000, precision = 0)
    @Basic
    private String commentText;
    @Column(name = "USER_NAME", nullable = false, insertable = true, updatable = true, length = 40, precision = 0)
    @Basic
    private String userName;
    @ManyToOne
    @JoinColumn(name = "ID_ITEM", referencedColumnName = "ID_ITEM", nullable = false)
    private Item item;

    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    public Date getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (idComment != comment.idComment) return false;
        if (commentText != null ? !commentText.equals(comment.commentText) : comment.commentText != null) return false;
        if (datePublished != null ? !datePublished.equals(comment.datePublished) : comment.datePublished != null)
            return false;
        if (userName != null ? !userName.equals(comment.userName) : comment.userName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idComment;
        result = 31 * result + (datePublished != null ? datePublished.hashCode() : 0);
        result = 31 * result + (commentText != null ? commentText.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "idComment=" + idComment +
                ", datePublished=" + datePublished +
                ", commentText='" + commentText + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
