package fix.infina.ocean.app.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

	public static void addMessageTo(quickfix.fix44.Message message, Integer field, Object value) {
		if (value instanceof Integer) {
			message.setInt(field, (int) value);
		} else if (value instanceof String) {
			message.setString(field, (String) value);
		} else if (value instanceof Character) {
			message.setChar(field, (Character) value);
		} else if (value instanceof Boolean) {
			message.setBoolean(field, (Boolean) value);
		} else if (value instanceof BigDecimal) {
			message.setDecimal(field, (BigDecimal) value);
		} else if (value instanceof Double) {
			message.setDouble(field, (Double) value);
		} else if (value instanceof Timestamp) {
			message.setUtcTimeStamp(field, new Date(((Timestamp) value).getTime()), true);
		} else if (value instanceof Date) {
			message.setString(field, new SimpleDateFormat("yyyyMMdd").format((Date) value));
		}
	}

}
