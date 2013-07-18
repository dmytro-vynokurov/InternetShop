package dao;

import entities.Comment;

import javax.ejb.Stateless;

/**
 * User: Dmytro_Vynokurov
 * Date: 6/25/13
 * Time: 4:10 PM
 */
@Stateless
public class CommentDAO extends GenericDAO<Comment> {
    @Override
    protected Class<Comment> entityClass() {
        return Comment.class;
    }
}
