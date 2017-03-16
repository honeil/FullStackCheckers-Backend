package team.squad;

import org.springframework.http.MediaType;

import java.nio.charset.Charset;

/**
 * Created by williammattern on 3/15/17.
 */
public class TestUtil {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8")
    );
}
