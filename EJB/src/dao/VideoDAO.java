package dao;

import entities.Video;

import javax.ejb.Stateless;

/**
 * User: Dmytro_Vynokurov
 * Date: 6/25/13
 * Time: 4:12 PM
 */
@Stateless
public class VideoDAO extends GenericDAO<Video> {
    @Override
    protected Class<Video> entityClass() {
        return Video.class;
    }
}
