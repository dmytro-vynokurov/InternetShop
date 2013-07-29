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
    private static final String FIND_VIDEOS_OF_ITEM = "findVideosOfItem";

    public List<Video> findVideosOfItem(final Item item) {
        return em.createNamedQuery(FIND_VIDEOS_OF_ITEM, Video.class)
                .setParameter("item", item)
                .getResultList();
    }

    public Video findVideoOfItem(Item item) {
        List<Video> availableVideos = findVideosOfItem(item);

        if(availableVideos.size()>0){
            Video result = new Video();
            result.setItem(item);
            result.setUrl(VIDEO_PREFIX + availableVideos.get(0).getUrl());
            return result;
        }
        else return null;
    }

    @Override
    protected Class<Video> entityClass() {
        return Video.class;
    }
}
