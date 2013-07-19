package dto.service;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

/**
 * User: Dmytro_Vynokurov
 * Date: 7/9/13
 * Time: 6:33 PM
 */
@ManagedBean
@ViewScoped
public class LoremIpsum implements Serializable {

    public String getShortText() {
        return "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Pellentesque molestie est ut erat malesuada viverra. " +
                "Nunc cursus tristique cursus. Curabitur quis erat vel dolor ultrices vestibulum vitae eget nibh. " +
                "Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; " +
                "Nunc et imperdiet leo, a sodales eros. Pellentesque non leo sit amet mi aliquet aliquam. " +
                "Proin in porta nibh, sit amet ullamcorper mauris. Pellentesque posuere rutrum dolor vel vehicula. " +
                "Vivamus ut fringilla arcu, id dapibus est. " +
                "Sed enim lacus, elementum id nisi non, lacinia aliquam leo.";
    }

    public String getText() {
        return "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam ultrices nulla a felis interdum scelerisque. " +
                "In euismod magna eu justo egestas luctus eu quis tortor. Nullam eget lobortis risus, et auctor justo. " +
                "Aenean congue urna erat, dignissim adipiscing lacus faucibus ac. Nullam et commodo nibh, quis volutpat arcu. " +
                "In bibendum elementum nulla sit amet hendrerit. Pellentesque interdum volutpat quam in interdum. " +
                "Vivamus nulla tellus, posuere dapibus risus at, luctus faucibus nunc. Maecenas turpis mi, porttitor vitae purus vitae, sodales vestibulum nulla. " +
                "Mauris a pellentesque augue. Fusce turpis dui, lacinia a velit ullamcorper, pharetra vehicula eros.\n" +
                "\n" +
                "Quisque tincidunt mauris et eros auctor, vel facilisis mi ultricies. Proin dictum scelerisque dui. " +
                "Fusce porta ut justo in bibendum. Sed ut elementum lacus. Ut nisl leo, pulvinar et risus non, adipiscing condimentum ligula. " +
                "Phasellus non massa in quam fermentum tempor id et urna. Fusce id scelerisque lacus, vitae eleifend mi. " +
                "Duis aliquet euismod ipsum, vitae posuere tellus mollis nec. Nam luctus porta pretium. Aenean blandit nunc accumsan porta ullamcorper. " +
                "Proin nec risus erat.\n" +
                "\n" +
                "Sed eu blandit leo. Duis commodo, massa ac hendrerit vehicula, ligula augue consequat nisl, ut rhoncus sapien quam a tellus. " +
                "Donec dignissim lectus hendrerit, accumsan erat in, adipiscing justo. Aenean vitae tincidunt felis. Nulla rhoncus ultrices sem a eleifend. " +
                "Sed commodo odio eu dui rutrum, eget tincidunt lacus euismod. Nam tempor velit odio, ut pretium diam sagittis eget. " +
                "Maecenas id facilisis mauris, non suscipit urna. Nunc tempor nibh mauris, vitae molestie odio vulputate pulvinar. " +
                "Pellentesque tempus vel massa vel imperdiet. Morbi sodales enim sollicitudin imperdiet tincidunt. Nulla facilisi. " +
                "Fusce nec libero ac diam volutpat vehicula. Aenean sit amet pellentesque nisl, eu euismod turpis. " +
                "Proin vitae mi vitae velit placerat suscipit at id elit. Nulla facilisis arcu non semper fringilla.\n" +
                "\n" +
                "Suspendisse potenti. Nam vitae felis cursus, semper leo id, scelerisque orci. Aliquam sit amet mi et ligula iaculis varius eu ac erat. " +
                "Sed in nisi ut lorem pharetra sodales quis sed dolor. Fusce accumsan dui ac placerat tristique. Nunc vel vestibulum orci, quis accumsan metus. " +
                "Curabitur eleifend gravida odio, in tempor urna vulputate ac.\n" +
                "\n" +
                "Nam laoreet, augue non aliquam consectetur, nibh odio cursus arcu, ac mattis nisi diam at nibh. " +
                "Aliquam ac semper erat, pretium iaculis dolor. In quis mi a sem ultricies ultricies non eget felis. " +
                "Duis congue dignissim ligula, et venenatis lectus bibendum eu. Ut fermentum, nunc vitae sodales sollicitudin, nunc urna pellentesque neque, " +
                "non auctor lacus justo in elit. Pellentesque id interdum leo. Sed vehicula neque quam, molestie dapibus nunc feugiat eu. " +
                "Pellentesque ut ornare lectus, eget sollicitudin nibh.";
    }
}
