package org.nlab.splunk.article.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * Created by nlabrot on 31/05/15.
 */
@Component
public class ScheduledTask {

    private static final String TEXT = "\n" +
            "\n" +
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas faucibus semper efficitur. Curabitur sit amet molestie tortor. Vivamus et quam vel ex interdum feugiat non vel magna. Maecenas suscipit mi sed lacinia malesuada. Donec accumsan diam vel purus elementum, quis bibendum lacus fermentum. Donec semper bibendum ipsum, a volutpat justo convallis sed. Nunc id nunc sit amet diam commodo sollicitudin id eu risus. Nullam at ante et risus venenatis luctus et at urna.\n" +
            "\n" +
            "Phasellus id accumsan purus. Donec aliquam, ligula sed rhoncus dictum, diam turpis aliquam nunc, vel dictum enim sapien et nulla. Donec sit amet condimentum erat. Donec pharetra erat nec nibh dignissim sagittis. Proin sit amet justo dapibus, fringilla libero non, vestibulum est. Vestibulum non arcu tortor. In nisi neque, aliquam sed est quis, aliquam volutpat dui. Aenean ornare ullamcorper interdum. Aliquam mi eros, fermentum ut interdum ut, pharetra eu orci. In neque mauris, tempor vitae pretium eu, vehicula at libero. Aliquam efficitur, tellus eu rutrum hendrerit, nibh nulla fringilla ipsum, eu maximus purus orci eget leo. Praesent vulputate purus ipsum, ac tristique enim euismod eget.\n" +
            "\n" +
            "Nam ullamcorper lacus sit amet venenatis egestas. Integer cursus a dolor non sagittis. Vestibulum pharetra feugiat ipsum, nec semper quam mattis vel. Quisque cursus dui in arcu ullamcorper dapibus sed vitae eros. Donec pulvinar velit aliquet libero mollis, vitae mollis libero rutrum. Praesent mollis leo nisl, at elementum eros porttitor eget. Vivamus lectus libero, tempus a ante ac, tristique congue justo. Donec ac vestibulum ex, quis tristique risus. Vivamus tincidunt aliquam augue, ac pellentesque ex finibus nec. Pellentesque tempor sagittis risus, at interdum nisi laoreet a. Nam nec odio ut erat molestie vestibulum. Integer ac sapien eu tortor dignissim dignissim aliquet a nulla. In quis consectetur sapien. Vestibulum nunc erat, pharetra sit amet dolor vitae, posuere aliquam ex.\n" +
            "\n" +
            "Nam et purus vitae elit dictum efficitur vitae ut lectus. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Donec pretium gravida auctor. Integer pellentesque luctus nisl et placerat. Nunc eros magna, semper ac leo non, sagittis sagittis felis. Donec commodo ligula nec velit tempus, sed lobortis diam dictum. Fusce et neque a nulla tincidunt consectetur. Etiam facilisis dui sed diam porttitor molestie. Morbi vitae dignissim dolor. Nunc congue leo nec est bibendum, at ullamcorper quam suscipit.\n" +
            "\n" +
            "Nam efficitur luctus mauris, a ultricies mi convallis ac. Pellentesque eu vehicula sem, in mollis lectus. Vestibulum non velit hendrerit neque vulputate viverra ac eu tortor. Praesent id risus viverra, cursus ipsum maximus, lobortis velit. Nam quis neque ex. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eleifend suscipit metus sit amet convallis. Mauris nibh purus, mattis ultricies mollis vel, iaculis in quam. Donec lectus ex, viverra non metus sit amet, elementum venenatis nibh. In eget nunc ut nibh tincidunt efficitur. Fusce id libero tortor. Nunc porta mauris enim, non feugiat eros molestie ut. Vestibulum suscipit feugiat varius. Donec porta gravida enim. Praesent ullamcorper ultrices ligula, nec imperdiet orci mattis vel. ";

    private static final Logger LOG = LoggerFactory.getLogger(ScheduledTask.class);

    @Scheduled(fixedRate = 2000)
    public void test() {
        int bound = current().nextInt(10000, 10000000);

        List<Integer> integers = new ArrayList<>(bound);

        for (int i = 0; i < bound; i++) {
            integers.add(new Integer(current().nextInt()));
        }
        LOG.debug("Sum={}", integers.stream().filter(i -> i < 5000).reduce((l, r) -> l + r).get());
    }


    @Scheduled(fixedRate = 2000)
    public void generateLogs() {
        int index = current().nextInt(TEXT.length() - 300);
        int length = current().nextInt(10, 300);

        if (current().nextInt(20) != 0) {
            LOG.info(TEXT.substring(index, index + length));
        } else {
            try{
                throw new Exception();
            }
            catch (Exception e){
                LOG.error(TEXT.substring(index, index + length) , e);
            }

        }
    }

}
