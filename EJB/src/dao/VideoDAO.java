package dao;

import entities.Item;
import entities.Video;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * User: Dmytro_Vynokurov
 * Date: 6/25/13
 * Time: 4:12 PM
 */
@Stateless
public class VideoDAO extends GenericDAO<Video> {
    private static final String VIDEO_PREFIX = "http://www.youtube.com/v/";

    public List<Video> findVideosOfItem(final Item item) {
        return executeQuery(new QueryBuilder() {
            @Override
            public TypedQuery<Video> buildQuery() {
                TypedQuery<Video> query = em.createQuery("SELECT v FROM Video v WHERE v.item=:item", Video.class);
                return query.setParameter("item", item);
            }
        });
    }

    public Video findVideoOfItem(Item item) {
        Video result = new Video();
        result.setItem(item);
        result.setUrl(VIDEO_PREFIX + findVideosOfItem(item).get(0).getUrl());
        return result;
    }

    @Override
    protected Class<Video> entityClass() {
        return Video.class;
    }
}
