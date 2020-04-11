package tw.b2e.receipt.execution;

public class ParamValueIsNullExecution extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public final static String REQUIRES_VALUE_FORMAT = "參數 %s 需要一個值. 參數說明請見'/receipt -help'";
	
	public ParamValueIsNullExecution() {super();};
	
	public ParamValueIsNullExecution(String nullParamName) {
		super(String.format(REQUIRES_VALUE_FORMAT, nullParamName));
	};
}
