package org.nlab.splunk.article.jmxtrans;

import com.github.nithril.logging.logback.json.argument.KeyValueArgument;
import com.github.nithril.logging.logback.json.argument.KeyValueArguments;
import org.jmxtrans.embedded.QueryResult;
import org.jmxtrans.embedded.output.AbstractOutputWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.github.nithril.logging.logback.json.argument.KeyValueArguments.argument;

/**
 * Created by nlabrot on 31/05/15.
 */
public class Slf4jWriter extends AbstractOutputWriter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void start() {
        super.start();
        logger = LoggerFactory.getLogger(getStringSetting("logger", getClass().getName()));
    }

    @Override
    public void write(Iterable<QueryResult> results) {
        List<KeyValueArgument> arguments = new ArrayList<>(10);
        for (QueryResult result : results) {
            arguments.add(argument(result.getName(), result.getValue()));
        }
        logger.info("", arguments.toArray(new KeyValueArgument[]{}));
    }
}
