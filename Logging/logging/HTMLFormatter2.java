package logging;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class HTMLFormatter2 extends Formatter{
    // this method is called for every log records
    public String format(LogRecord rec) {
        StringBuffer buf = new StringBuffer(1000);
        buf.append("<tr>\n");

        // colorize any levels >= WARNING in red
        if (rec.getLevel().intValue() >= Level.WARNING.intValue()) {
            buf.append("\t<td style=\"color:red\">");
            buf.append("<b>");
            buf.append(rec.getLevel());
            buf.append("</b>");        buf.append("</td>\n");
            buf.append("\t<td style=\"color:red\">");
            buf.append(calcDate(rec.getMillis()));
            buf.append("</td>\n");
            buf.append("\t<td style=\"color:red\">");
            buf.append(formatMessage(rec));
            buf.append("</td>\n");
            buf.append("</tr>\n");
        } else if(rec.getLevel().intValue() == Level.FINE.intValue()){
            buf.append("\t<td style=\"color:navy\">");
            buf.append("<b>");
            buf.append(rec.getLevel());
            buf.append("</b>");
            buf.append("</td>\n");
            buf.append("\t<td style=\"color:navy\">");
            buf.append(calcDate(rec.getMillis()));
            buf.append("</td>\n");
            buf.append("\t<td style=\"color:navy\">");
            buf.append(formatMessage(rec));
            buf.append("</td>\n");
            buf.append("</tr>\n");
        }else if(rec.getLevel().intValue() == Level.FINER.intValue()){
            buf.append("\t<td style=\"color:blue\">");
            buf.append("<b>");
            buf.append(rec.getLevel());
            buf.append("</b>");
            buf.append("</td>\n");
            buf.append("\t<td style=\"color:blue\">");
            buf.append(calcDate(rec.getMillis()));
            buf.append("</td>\n");
            buf.append("\t<td style=\"color:blue\">");
            buf.append(formatMessage(rec));
            buf.append("</td>\n");
            buf.append("</tr>\n");
        }else if(rec.getLevel().intValue() == Level.FINEST.intValue()){
            buf.append("\t<td style=\"color:aqua\">");
            buf.append("<b>");
            buf.append(rec.getLevel());
            buf.append("</b>");
            buf.append("</td>\n");
            buf.append("\t<td style=\"color:aqua\">");
            buf.append(calcDate(rec.getMillis()));
            buf.append("</td>\n");
            buf.append("\t<td style=\"color:aqua\">");
            buf.append(formatMessage(rec));
            buf.append("</td>\n");
            buf.append("</tr>\n");
        } else if(rec.getLevel().intValue() == Level.CONFIG.intValue()){
            buf.append("\t<td style=\"color:lime\">");
            buf.append("<b>");
            buf.append(rec.getLevel());
            buf.append("</b>");
            buf.append("</td>\n");
            buf.append("\t<td style=\"color:lime\">");
            buf.append(calcDate(rec.getMillis()));
            buf.append("</td>\n");
            buf.append("\t<td style=\"color:lime\">");
            buf.append(formatMessage(rec));
            buf.append("</td>\n");
            buf.append("</tr>\n");
    	}else {
            buf.append("\t<td style=\"color:gray\">");
            buf.append(rec.getLevel());
            buf.append("</td>\n");
            buf.append("\t<td style=\"color:gray\">");
            buf.append(calcDate(rec.getMillis()));
            buf.append("</td>\n");
            buf.append("\t<td style=\"color:gray\">");
            buf.append(formatMessage(rec));
            buf.append("</td>\n");
            buf.append("</tr>\n");
        }



        return buf.toString();
    }

    private String calcDate(long millisecs) {
        SimpleDateFormat date_format = new SimpleDateFormat("MMM dd,yyyy HH:mm");
        Date resultdate = new Date(millisecs);
        return date_format.format(resultdate);
    }

    // this method is called just after the handler using this
    // formatter is created
    public String getHead(Handler h) {
        return "<!DOCTYPE html>\n<head>\n<style>\n"
            + "table { width: 100% }\n"
            + "th { font:bold 10pt Tahoma;}\n"
            + "td { font:normal 10pt Tahoma;}\n"
            + "h1 {font:normal 11pt Tahoma;}\n"
            + "body {background-color: black;}"
            + "</style>\n"
            + "</head>\n"
            + "<body>\n"
            + "<h1>" + (new Date()) + "</h1>\n"
            + "<table border=\"3\" cellpadding=\"5\" cellspacing=\"3\">\n"
            + "<tr align=\"left\">\n"
            + "\t<th style=\"width:10%\">Loglevel</th>\n"
            + "\t<th style=\"width:15%\">Time</th>\n"
            + "\t<th style=\"width:75%\">Log Message</th>\n"
            + "</tr>\n";
      }

    // this method is called just after the handler using this
    // formatter is closed
    public String getTail(Handler h) {
        return "</table>\n</body>\n</html>";
    }
}
