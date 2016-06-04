package org.kszidocs.util;


public class MimeType {

    private static final String DOCX_MIME_TYPE="application/vnd.openxmlformats-officedocument.wordprocessingml.document";
    private static final String DOC_MIME_TYPE="application/msword";
    private static final String PDF_MIME_TYPE="application/pdf";

    private static final String DOC = ".doc";
    private static final String DOCX = ".docx";
    private static final String PDF = ".pdf";

    public static String getType(String fileExtension) {
        if(fileExtension.equals(DOC)) {
            return DOC_MIME_TYPE;
        } else if(fileExtension.equals(DOCX)) {
            return DOCX_MIME_TYPE;
        } else if(fileExtension.equals(PDF)) {
            return PDF_MIME_TYPE;
        } else {
            throw new IllegalArgumentException("");
        }
    }
    //TODO: implement if needed
    /*
    * .doc      application/msword
    .dot      application/msword

    .docx     application/vnd.openxmlformats-officedocument.wordprocessingml.document
    .dotx     application/vnd.openxmlformats-officedocument.wordprocessingml.template
    .docm     application/vnd.ms-word.document.macroEnabled.12
    .dotm     application/vnd.ms-word.template.macroEnabled.12

    .xls      application/vnd.ms-excel
    .xlt      application/vnd.ms-excel
    .xla      application/vnd.ms-excel

    .xlsx     application/vnd.openxmlformats-officedocument.spreadsheetml.sheet
    .xltx     application/vnd.openxmlformats-officedocument.spreadsheetml.template
    .xlsm     application/vnd.ms-excel.sheet.macroEnabled.12
    .xltm     application/vnd.ms-excel.template.macroEnabled.12
    .xlam     application/vnd.ms-excel.addin.macroEnabled.12
    .xlsb     application/vnd.ms-excel.sheet.binary.macroEnabled.12

    .ppt      application/vnd.ms-powerpoint
    .pot      application/vnd.ms-powerpoint
    .pps      application/vnd.ms-powerpoint
    .ppa      application/vnd.ms-powerpoint

    .pptx     application/vnd.openxmlformats-officedocument.presentationml.presentation
    .potx     application/vnd.openxmlformats-officedocument.presentationml.template
    .ppsx     application/vnd.openxmlformats-officedocument.presentationml.slideshow
    .ppam     application/vnd.ms-powerpoint.addin.macroEnabled.12
    .pptm     application/vnd.ms-powerpoint.presentation.macroEnabled.12
    .potm     application/vnd.ms-powerpoint.template.macroEnabled.12
    .ppsm     application/vnd.ms-powerpoint.slideshow.macroEnabled.12
    * */
}
