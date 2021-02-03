package com.crh.wxbase.gen.generator;

import com.alibaba.excel.metadata.Sheet;
import com.crh.wxbase.common.constant.CommonConsts;
import com.crh.wxbase.gen.dto.GenExcelModel;
import com.crh.wxbase.common.utils.ExcelUtil;
import com.google.common.base.CaseFormat;

import java.io.*;
import java.util.List;

/**
 * @author rory.chen
 * @date 2021-01-06 20:18
 */
public class InsureGen {

    private static final String FILENAME = "UnderwriteResData";
    private static final String PACKAGEPATH = "taikangcx.domian.dto.manager.underwrite;";

    /**
     * excel 格式为 参数名、注释、类型、备注说明
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        readLessThan1000Row(CommonConsts.GEN_WINDOW_PATH + "excel.xlsx", FILENAME);
    }

    /**
     * 读取少于1000行数据
     *
     * @param filePath 文件绝对路径
     * @return
     */
    public static void readLessThan1000Row(String filePath, String writeFileName) {
        Sheet sheet = new Sheet(1, 1, GenExcelModel.class);
        List<Object> excelModels = ExcelUtil.readLessThan1000RowBySheet(filePath, sheet);

        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter(CommonConsts.GEN_WINDOW_PATH + "code//" + writeFileName + ".java");
            bufferedWriter = new BufferedWriter(fileWriter);

            writeBaseEntityLine(bufferedWriter, writeFileName);
            bufferedWriter.newLine();
            for (Object excelModel : excelModels) {
                GenExcelModel genExcelModel = (GenExcelModel) excelModel;
                genExcelModel.setColumnName(genExcelModel.getColumnName().replace(" ", ""));
                writeLine(bufferedWriter, "    /**");
                writeLine(bufferedWriter, "     * " + genExcelModel.getAnnotation());
                writeLine(bufferedWriter, "     * " + genExcelModel.getRemark());
                writeLine(bufferedWriter, "     */");
                writeLine(bufferedWriter, "    @JsonProperty(\"" + genExcelModel.getColumnName() + "\")");
                writeLine(bufferedWriter, "    private " + getColumnType(genExcelModel) + " "
                        + initialToCapital(genExcelModel.getColumnName(), false) + ";");
                bufferedWriter.newLine();
            }
            writeLine(bufferedWriter, "}");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedWriter.close();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取字段的类型
     *
     * @param genExcelModel
     * @return
     */
    private static String getColumnType(GenExcelModel genExcelModel) {
        if ("String".equals(genExcelModel.getType())) {
            return "String";
        } else if ("Number".equals(genExcelModel.getType())) {
            return "Integer";
        } else if ("Long".equals(genExcelModel.getType())) {
            return "Long";
        } else if ("List".equals(genExcelModel.getType())) {
            return "List<" + initialToCapital(genExcelModel.getColumnName(), true) + ">";
        } else if ("Map".equals(genExcelModel.getType())) {
            return initialToCapital(genExcelModel.getColumnName(), true);
        }
        return null;
    }


    /**
     * 首字母转大写、驼峰命名
     *
     * @param columnName 待转换的字符串
     * @return 转换结果
     */
    public static String initialToCapital(String columnName, Boolean firstUpper) {
        if (columnName.contains("_")) {
            columnName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, columnName);
        }
        if (firstUpper) {
            columnName = columnName.substring(0, 1).toUpperCase() + columnName.substring(1);
        }
        return columnName;
    }


    /**
     * 写入基础数据
     *
     * @param bufferedWriter
     * @throws IOException
     */
    private static void writeBaseEntityLine(BufferedWriter bufferedWriter, String sheetName) throws IOException {
        writeLine(bufferedWriter, "package com.jfz.ins.insure.plugin." + PACKAGEPATH);
        bufferedWriter.newLine();
        writeLine(bufferedWriter, "import com.fasterxml.jackson.annotation.JsonProperty;");
        writeLine(bufferedWriter, "import lombok.Data;");
        bufferedWriter.newLine();
        writeLine(bufferedWriter, "/**");
        writeLine(bufferedWriter, " * @author rory.chen");
        writeLine(bufferedWriter, " * @date 2021-01-07 15:48");
        writeLine(bufferedWriter, " */");
        writeLine(bufferedWriter, "@Data");
        writeLine(bufferedWriter, "public class " + sheetName + " {");
    }


    /**
     * 写一行数据
     */
    private static void writeLine(BufferedWriter bufferedWriter, String lineText) throws IOException {
        bufferedWriter.write(lineText);
        bufferedWriter.newLine();
    }

}
