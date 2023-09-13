import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 根据前端文案和描述生成 SQL
 *
 * Created by wuyufan on 2023/9/11.
 */
public class FileProcessor {
    public static void main(String[] args) {
        String inputFile = "D:\\Users\\WIN10\\Documents\\code\\studyDemo\\file\\Common.json";

        // 报错：	"Common/Award/Tips/OtherAward_10001_10199":"用于升级球员的第1个技能，可在<color=#ec7b02>巡回赛商店</color>，<color=#ec7b02>商店</color>或者<color=#ec7b02>活动</color>中获得",
        // 报错：	"Common/Award/Tips/OtherAward_10201_10498":"用于升级球员的第2个技能，可在<color=#ec7b02>联盟商店</color>，<color=#ec7b02>商店</color>或者<color=#ec7b02>活动</color>中获得",
        // 报错：	"Common/Award/Tips/OtherAward_10500_10999":"用于升级球员的第3个技能，可在<color=#ec7b02>联盟之战商店</color>，<color=#ec7b02>商店</color>或者<color=#ec7b02>活动</color>中获得",
        // 报错：	"Common/Award/Tips/OtherAward_10701_12000":"用于升级球员的第3个技能，可在<color=#ec7b02>活动</color>，<color=#ec7b02>商店</color>中获得",
        // 报错：	"Common/Award/Tips/OtherAward_12007_0":"随机获得活动球员的专属陪练教练",
        // 报错：	"Common/Award/Tips/OtherAward_15501_15519":"用于技能传承指导，可在活动、礼包中获得",
        // 报错：	"Common/Award/Tips/OtherAward_26237_1":"用于制作周年庆蛋糕。",
        // 报错：	"Common/Award/Tips/OtherAward_26237_2":"用于裁剪新春窗花。",
        String outputFile = "D:\\Users\\WIN10\\Documents\\code\\studyDemo\\file\\Common.sql";
        String prefix = "Common/Award/Tips/OtherAward_";



        // 报错：	"Common/Award/OtherAward_12007_0":"随机专属陪练教练",
        // 报错：	"Common/Award/OtherAward_26237_1":"蛋糕原料",
        // 报错：	"Common/Award/OtherAward_26237_2":"剪纸工具",
        // String outputFile = "D:\\Users\\WIN10\\Documents\\code\\studyDemo\\file\\Common2.sql";
        // String prefix = "Common/Award/OtherAward_";

        String itemDesc = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, false))) {
            writer.write("REPLACE INTO t_item_desc (id, item_desc) VALUES");
            writer.newLine();

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(prefix)) {
                    String numberStr = "";
                    int number = 0;
                    try {
                        itemDesc = line.substring(prefix.length() + line.indexOf(prefix));
                        numberStr = itemDesc.substring(0, itemDesc.indexOf("\""));
                        number = Integer.parseInt(numberStr);
                    } catch (NumberFormatException e) {
                        // System.err.println("报错：" + line);
                        String[] split = numberStr.split("_");
                        int startNum = Integer.parseInt(split[0]);
                        int endNum = Integer.parseInt(split[1]);
                        if (startNum > endNum) { // 只写出一个
                            writeDesc(writer, itemDesc, startNum);
                        } else { // 写出范围
                            for (int i = startNum; i <= endNum; i++) {
                                writeDesc(writer, itemDesc, i);
                            }
                        }
                        continue;
                    }

                    writeDesc(writer, itemDesc, number);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeDesc(BufferedWriter writer, String itemDesc, int number) throws IOException {
        String substring = itemDesc.substring(0, itemDesc.lastIndexOf("\""));
        String rs = substring.substring(substring.lastIndexOf("\"") + 1);
        // REPLACE INTO t_item_desc (id, item_desc) VALUES(0, '');
        writer.write(String.format("(%s, '%s'),", number, rs));
        writer.newLine();
    }
}
