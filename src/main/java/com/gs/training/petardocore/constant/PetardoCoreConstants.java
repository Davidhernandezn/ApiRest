package com.gs.training.petardocore.constant;

/**
 * <b>PetardoCoreConstants.java</b>
 *
 * @version: ts-demo-base-v1 1.0
 * @description: PetardoCoreConstants Class
 * @author: CoE Microservicios
 */
public final class PetardoCoreConstants {

	/**
	 * Value for Util class
	 */
	public static final int VAL5 = 5;
	/**
	 * The constant SUCCESSFUL_OPERATION_STATUS.
	 */
	public static final String SUCCESSFUL_OPERATION_STATUS = "Successful operation";
	/**
	 * The constant PARAM_NULL.
	 */
	public static final String PARAM_NULL = "Incorrect input parameters, please validate your information.";
	/**
	 * The constant PRO_IN_SERVER.
	 */
	public static final String PRO_IN_SERVER = "Internal Server Error, please validate.";
	/**
	 * The constant DETAILS_EXCEPTION_500
	 */
	public static final String DETAILS_EXCEPTION_500 = "Request Error";
	/**
	 * The constant HEADER_DATE_REQUIRED.
	 */
	public static final String HEADER_DATE_REQUIRED = "The header 'headerDate' is required";
	/**
	 * The constant PROJECT_MSJ_EXCEPTION.
	 */
	public static final String PROJECT_MSJ_EXCEPTION = "Project-ts-demo-base-v1";
	/**
	 * The constant SUCCESSFUL_REQUEST_MESSAGE.
	 */
	public static final String SUCCESSFUL_REQUEST_MESSAGE = "Successful operation";
	/**
	 * The constant MSJ_REQUIRED_FIELD.
	 */
	public static final String MSJ_REQUIRED_FIELD = "Required Field: ";
	/**
	 * The constant MSJ_REQUIRED_BODY.
	 */
	public static final String MSJ_REQUIRED_BODY = "Required Body: ";
	/**
	 * The constant DATE_FORMAT_UTC.
	 */
	public static final String DATE_FORMAT_UTC = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
	/**
	 * The constant DATE_FORMAT_FOLIO.
	 */
	public static final String DATE_FORMAT_FOLIO = "yyyyMMddHHmmssSSS";
	/**
	 * The constant ZONE_ID.
	 */
	public static final String ZONE_ID = "America/Mexico_City";
	/**
	 * The constant URL_DEVELOPER_INFORMATION_CODES_DEFAULT.
	 */
	public static final String URL_DEVELOPER_INFORMATION_CODES_DEFAULT = "https://baz-developer.bancoazteca.com.mx/info#";
	/**
	 * The constant URL_DEVELOPER_INFORMATION_CODES.
	 */
	public static final String URL_DEVELOPER_INFORMATION_CODES = "https://dev-api.bancoazteca.com.mx/info";
	/**
	 * The constant PATH_STATUS
	 */
	public static final String PATH_STATUS = "/status" + "";
	/**
	 * The constant LOG_ERROR.
	 */
	public static final String LOG_ERROR = "Excepcion: {} | {}";
	/**
	 * The constant USER_HEADER.
	 */
	public static final String USER_HEADER = "x-user-token";
	/**
	 * The constant USER_HEADER_TOKEN.
	 */
	public static final String USER_HEADER_TOKEN = "x-token-autorizacion";
	/**
	 * The constant PATH_CONTROLLER.
	 */
	public static final String PATH_CONTROLLER = "/petardocore" + "";
	/**
	 * The constant PATH_CONTROLLER_GET_ID.
	 */
	public static final String PATH_CONTROLLER_GET_ID = "/petardocore/{id}" + "";
	/**
	 * The constant BASE_PATH DEFAULT
	 */
	public static final String BASE_PATH_DEFAULT = "/ts/demo/base/v1" + "";
	/**
	 * The constant BASE_PATH
	 */
	public static final String BASE_PATH = "/banco-azteca/afore/gestion-personas/v1/" + "";
	/**
	 * The constant E400.
	 */
	public static final String E400 = "E400";
	/**
	 * The constant E401.
	 */
	public static final String E401 = "E401";
	/**
	 * The constant E404.
	 */
	public static final String E404 = "E404";
	/**
	 * The constant DETAIL.
	 */
	public static final String DETAIL = "detail";
	/**
	 * The constant FOLIO_TRACE.
	 */
	public static final String FOLIO_TRACE = "x-id-interaction";

	/**
	 * The constant USUARIO_INTERNO_HEADER.
	 */
	public static final String USUARIO_INTERNO_HEADER = "x-usuario-interno";

	/**
	 * The constant USUARIO_EXTERNO_HEADER.
	 */
	public static final String USER_EXTERNAL_HEADER = "x-usuario-externo";
	/**
	 * The constant PATH_VALIDACIONES
	 */
	public static final String PATH_VALIDACIONES = "/validaciones";;
	// APIGEECONSTANT
	// IDACCESOCONSTANT

	/**
	 * Private constructor, added to overwrite the public implicit and the class
	 * cannot be instantiated by other packages.
	 */
	private PetardoCoreConstants() {

		super();
	}
}
