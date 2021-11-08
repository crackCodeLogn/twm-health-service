package com.vv.personal.twm.health.constants;

import com.vv.personal.diurnal.artifactory.generated.EntryDayProto;
import com.vv.personal.diurnal.artifactory.generated.ResponsePrimitiveProto;
import com.vv.personal.diurnal.artifactory.generated.UserMappingProto;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Vivek
 * @since 23/12/20
 */
public final class Constants {

    private Constants() {
    }

    public static final String EMPTY_STR = "";
    public static final String NEW_LINE = "\n";
    public static final String COMMA_STR = ",";
    public static final String REPLACE_JSON_DI = "`@%"; //DI - double inverts
    public static final String PIPE = "|";
    public static final String ENTRIES_SQL_DATA_SEPARATOR = "%~@";

    public static final int NA_INT = -1;
    public static final int ONE = 1;
    public static final double DEFAULT_AMOUNT = 0.0;
    public static final long NA_LONG = -1L;

    public static final String APPLICATION_X_PROTOBUF = "application/x-protobuf";

    //RESPONSES
    public static final Integer INT_RESPONSE_WONT_PROCESS = -13; //N Proc

    public static final ResponsePrimitiveProto.ResponsePrimitive RESPOND_FALSE_BOOL = ResponsePrimitiveProto.ResponsePrimitive.newBuilder().setBoolResponse(false).build();
    public static final ResponsePrimitiveProto.ResponsePrimitive RESPOND_TRUE_BOOL = ResponsePrimitiveProto.ResponsePrimitive.newBuilder().setBoolResponse(true).build();
    public static final ResponsePrimitiveProto.ResponsePrimitive RESPOND_EMPTY_BODY = ResponsePrimitiveProto.ResponsePrimitive.newBuilder().setResponse(EMPTY_STR).build();

    public static final DateTimeFormatter DTF_ENTRY_DAY_DATE_PATTERN = DateTimeFormatter.ofPattern("yyyyMMdd");
    public static final DateTimeFormatter DTF_APP_DISPLAY_DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static final Instant DEFAULT_INSTANT_DATETIME = Instant.ofEpochMilli(0L);
}