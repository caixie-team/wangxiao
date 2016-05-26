package io.wangxiao.core.util;

import io.wangxiao.core.BaseService;
import io.wangxiao.core.BaseDao;
import io.wangxiao.core.model.BaseIncrementIdModel;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPictureData;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.XmlObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 导入Execl文件抽象类
 */
public abstract class AbstExcelImportBiz<T extends BaseIncrementIdModel, K extends BaseDao<T>>
        extends BaseService<T, K> {



    protected int BATCH_SAVE_SIZE = 2000;

    private boolean isSkipFirstLine = true;

    private boolean isSavePictrue = false;// 保存EXCEL文档中的图片

    // 子类有可能要用到，所以必须写成成员变量
    protected ThreadLocal<ImageRelations> imageRelations = new ThreadLocal<ImageRelations>() {
        protected synchronized ImageRelations initialValue() {
            return new ImageRelations();
        }
    };

    public Map<String, String> getRelations() {
        return imageRelations.get().relations;
    }

    public Map<String, PictureData> getPictures() {
        return imageRelations.get().pictures;
    }

    public void addRelation(int row, int col, String picId) {
        getRelations().put(row + "_" + col, picId);
    }

    public void addPicture(String picId, PictureData picData) {
        getPictures().put(picId, picData);
    }

    public PictureData getPictureContent(int row, int col) {
        return getPictures().get(getRelations().get(row + "_" + col));
    }

    public void setIsSavePictrue(boolean isSavePictrue) {
        this.isSavePictrue = isSavePictrue;
    }

    public boolean isSavePictrue() {
        return this.isSavePictrue;
    }

    public void setIsSkipFirstLine(boolean isSkipFirstLine) {
        this.isSkipFirstLine = isSkipFirstLine;
    }

    public boolean isSkipFirstLine() {
        return isSkipFirstLine;
    }

    /**
     * 获取EXCEL2007以上版本工作簿引用
     */
    public XSSFWorkbook getXSSFWorkbook(InputStream is) {
        XSSFWorkbook wb = null;
        try {
            wb = new XSSFWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
//            log.error("获取EXCEL工作簿失败！", e);
            throw new RuntimeException("获取EXCEL工作簿失败！");
        }
        return wb;
    }

    /**
     * 获取EXCEL2007以上版本工作簿中的表格
     *
     * @return
     */
    public XSSFSheet getXSSFSheet(XSSFWorkbook workbook, int sheetIndex) {
        return workbook.getSheetAt(sheetIndex);
    }

    /**
     * 获取EXCEL2007以上版本工作簿中的第一个表格
     *
     * @return
     */
    public XSSFSheet getXSSFSheet0(XSSFWorkbook workbook) {
        return getXSSFSheet(workbook, 0);
    }

    public void closeInputStream(InputStream is) {
        if (is != null) {
            try {
                is.close();
//                log.debug("...输入流关闭成功");
            } catch (IOException e) {
//                log.error(e);
                e.printStackTrace();
            }
        }
    }



    /**
     * 从excel中导入试题
     *
     * @param filePath 文件在磁盘上的绝对路径
     */
    public void importFromExcel(String filePath) {

        InputStream is = null;
        XSSFWorkbook wb = null;
        List<T> entities = new ArrayList<T>();
        List<String> errorList = new ArrayList<String>();

        if (!validateFileType(filePath)) {
            throw new RuntimeException("文件格式不正确！");
        }

        try {
            is = new FileInputStream(filePath);
            wb = new XSSFWorkbook(is);
        } catch (Exception e) {
//            log.error("读取试题EXCEL文档失败！", e);
            e.printStackTrace();
            throw new RuntimeException("读取试题EXCEL文档失败！", e);
        }

        XSSFSheet sheet = wb.getSheetAt(0);

        Iterator<Row> it = sheet.rowIterator();
        if (isSkipFirstLine() && it.hasNext()) {
//            log.debug("SKIP_FIRST_LINE");
            it.next();
        }

        if (isSavePictrue()) {
            findAllPicture(sheet);
        }

//        if (log.isDebugEnabled()) {
//            try {
//                imageRelations.get().testPrintRelations();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        T entity = null;
        while (it.hasNext()) {
            Row row = it.next();

            entity = fillBean(row);

            if (entity != null)
                validateContent(errorList, entity, row.getRowNum());

            if (errorList.size() == 0 && entity != null)
                entities.add(entity);

            if (entities.size() >= BATCH_SAVE_SIZE)
                saveBatchEntities(entities);

            entity = null;
        }
        if (errorList.size() != 0) {
            closeInputStream(is);
            throw new RuntimeException(outputErrorMessage(errorList));
        }

        if (entities.size() > 0) {
            saveBatchEntities(entities);
        }

        closeInputStream(is);
        entities = null;
        errorList = null;
    }



    /**
     * 从excel中导入试题
     *
     * @param filePath 文件在磁盘上的绝对路径
     */
    public void importFromExcel(String filePath,Integer questiontype,Long categoryid) {

        InputStream is = null;
        XSSFWorkbook wb = null;
        List<T> entities = new ArrayList<T>();
        List<String> errorList = new ArrayList<String>();

        if (!validateFileType(filePath)) {
            throw new RuntimeException("文件格式不正确！");
        }

        try {
            is = new FileInputStream(filePath);
            wb = new XSSFWorkbook(is);
        } catch (Exception e) {
//            log.error("读取试题EXCEL文档失败！", e);
            e.printStackTrace();
            throw new RuntimeException("读取试题EXCEL文档失败！", e);
        }

        XSSFSheet sheet = wb.getSheetAt(0);

        Iterator<Row> it = sheet.rowIterator();
        if (isSkipFirstLine() && it.hasNext()) {
//            log.debug("SKIP_FIRST_LINE");
            it.next();
        }

        if (isSavePictrue()) {
            findAllPicture(sheet);
        }

//        if (log.isDebugEnabled()) {
//            try {
//                imageRelations.get().testPrintRelations();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        T entity = null;
        while (it.hasNext()) {
            Row row = it.next();

            entity = fillBean(row,questiontype,categoryid);

            if (entity != null)
                validateContent(errorList, entity, row.getRowNum());

            if (errorList.size() == 0 && entity != null)
                entities.add(entity);

            if (entities.size() >= BATCH_SAVE_SIZE)
                saveBatchEntities(entities);

            entity = null;
        }
        if (errorList.size() != 0) {
            closeInputStream(is);
            throw new RuntimeException(outputErrorMessage(errorList));
        }

        if (entities.size() > 0) {
            saveBatchEntities(entities);
        }

        closeInputStream(is);
        entities = null;
        errorList = null;
    }

    protected String outputErrorMessage(List<String> errorList) {
        StringBuilder sb = new StringBuilder();
        for (String msg : errorList) {
            sb.append(msg);
        }
        return sb.toString();
    }

    /**
     * 校验文档内容 诸如必填项之类
     *
     * @param errorList
     * @param entity
     * @param rowCursor
     */
    abstract protected void validateContent(List<String> errorList, T entity,
                                            int rowCursor);

    /**
     * 校验文件类型
     *
     * @param filePath
     * @return
     */
    protected boolean validateFileType(String filePath) {
        File excel = new File(filePath);
        String fileName = excel.getName();
        if (fileName != null && !"".equals(fileName.trim())) {
            if (fileName.endsWith(".xlsx")) {
                return true;
            }
        }
        return false;
    }

    /**
     * 将excel文件中的一行数据填充到一个bean中
     *
     * @param row
     * @return
     * @throws java.text.ParseException
     */
    abstract protected T fillBean(Row row);

    abstract protected T fillBean(Row row,Integer questiontype,Long categoryid);

    protected void findAllPicture(XSSFSheet sheet) {

        for (Object o : sheet.getRelations()) {

            if (o instanceof XSSFDrawing) {

                XSSFDrawing drawing = (XSSFDrawing) o;
                XmlObject[] positionRelations = drawing
                        .getCTDrawing()
                        .selectPath(
                                "declare namespace s='http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing' "
                                        + ".//s:twoCellAnchor//s:from");

                XmlObject[] relations = drawing
                        .getCTDrawing()
                        .selectPath(
                                "declare namespace s='http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing'  declare namespace a='http://schemas.openxmlformats.org/drawingml/2006/main'"
                                        + ".//s:twoCellAnchor//s:pic//s:blipFill/a:blip");

                for (int i = 0; i < relations.length; i++) {

                    XmlObject relation = relations[i];
                    XmlObject positionRelation = positionRelations[i];

                    String col = positionRelation.getDomNode().getChildNodes()
                            .item(0).getFirstChild().getNodeValue();
                    String row = positionRelation.getDomNode().getChildNodes()
                            .item(2).getFirstChild().getNodeValue();

                    String picId = relation.getDomNode().getAttributes()
                            .getNamedItem("r:embed").getNodeValue();
                    addRelation(Integer.valueOf(row), Integer.valueOf(col),
                            picId);

                }

            }
        }

        List lst = sheet.getWorkbook().getAllPictures();
        for (Object aLst : lst) {
            PictureData pict = (PictureData) aLst;
            XSSFPictureData pd = (XSSFPictureData) pict;
            addPicture(pd.getPackageRelationship().getId(), pd);
        }

    }

    /**
     * 批量保存实体
     *
     * @param entities
     */
    protected void saveBatchEntities(List<T> entities) {
        super.saveBatch(entities);
        entities.clear();
    }

    /**
     * 获取单元格内容的通用方法
     *
     * @param cell
     * @return
     */
    protected String getCellValue(Cell cell) {

        if (cell == null)
            return "";

        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue().trim();
            case Cell.CELL_TYPE_NUMERIC:
                return String.valueOf(cell.getNumericCellValue()).trim();
            case Cell.CELL_TYPE_BLANK:
                return cell.getStringCellValue().trim();
            default:
                return "";
        }

    }


    protected String converStrIntoImgTag(String str) {
        StringBuilder sb = new StringBuilder("<IMG SRC=\"");
        sb.append(str);
        sb.append("\" />");
        return sb.toString();
    }

    class ImageRelations {
        private Map<String, String> relations = new HashMap<String, String>();
        private Map<String, PictureData> pictures = new HashMap<String, PictureData>();

        public void addRelation(int row, int col, String picId) {
            relations.put(row + "_" + col, picId);
        }

        public void addPicture(String picId, PictureData picData) {
            pictures.put(picId, picData);
        }

        public PictureData getPictureContent(int row, int col) {
            return pictures.get(relations.get(row + "_" + col));
        }

        public void testPrintRelations() throws IOException {
            for (String key : relations.keySet()) {
                PictureData pd = pictures.get(relations.get(key));
                FileUtils.writeByteArrayToFile(new File("d:/excelPic/" + key
                        + "." + pd.suggestFileExtension()), pd.getData());

            }
        }

    }

}
