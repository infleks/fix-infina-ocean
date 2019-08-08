package fix.infina.ocean.model;

import quickfix.fix44.Message;

public class OceanURTMessage extends Message {

	private static final long serialVersionUID = 7185183269123795756L;

	public static final String MSGTYPE = "URT";

	public OceanURTMessage() {
		super();
		getHeader().setField(new quickfix.field.MsgType(MSGTYPE));
	}

}
