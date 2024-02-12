//package sample;
//
//import com.gembox.spreadsheet.*;
//import javafx.stage.FileChooser;
//
//import java.io.File;
//import java.io.IOException;
//
//public class WorkBook {
//    private ExcelWorksheet workSheet;
//
//    static {
//        SpreadsheetInfo.setLicense("FREE-LIMITED-KEY");
//    }
//
//    public WorkBook() {
//        try {
//            FileChooser fileChooser = new FileChooser();
//            fileChooser.setTitle("Open file");
//
//            File file = new File("Quiz.xlsx");
//
//            ExcelFile workBook = ExcelFile.load(file.getAbsolutePath());
//            this.workSheet = workBook.getWorksheet(0);
//
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public String[] getColumn(int index) {
//        ExcelColumn column = this.workSheet.getColumn(index);
//        String[] str = new String[10];
//
//        for (int i = 1; i <= 10 && column.getCell(i).getValueType() != CellValueType.NULL; i++)
//            str[i] = column.getCell(i).toString();
//
//        return str;
//    }
//}
