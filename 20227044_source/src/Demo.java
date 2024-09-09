// Name: Nguyễn Trường Giang
// Student ID: 20227044
// Class: MI1 - K67 - 150327
// Project: 02 - Xây dựng thư viện phép toán với sô nguyên lớn
// Date: 19/6/2024

import biginteger.BigInt;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Demo {

    //Hàm lưu phép tính vừa xong vào file output để người dùng theo dõi
    public static void writeInOutput(String x, String y, String result){
        try (PrintWriter writer = new PrintWriter(new FileWriter("output.txt"))) {
            writer.println(x + " + " + y + " = " + result);
        } catch (IOException e) {
            System.err.println("Lỗi khi ghi vào file " + "output.txt" + ": " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        //Hiển thị các lựa chọn ra màn hình
        System.out.println("===============Menu===============");
        System.out.println("1 - Cộng 2 số nguyên");
        System.out.println("2 - Trừ 1 số nguyên cho 1 số nguyên");
        System.out.println("3 - Nhân 2 số nguyên");
        System.out.println("4 - Chia 1 số nguyên cho 1 số nguyên");
        System.out.println("5 - Thoát khỏi máy tính");
        
        //nhập yêu cầu của người dùng
        int choice = 0;
        while (choice != 5) {
            System.out.print("Bạn muốn thực hiện phép toán nào: ");
            choice = Integer.parseInt(System.console().readLine());
            if (choice < 1 || choice > 5) {
                System.out.println("Lựa chọn không hợp lệ");
                continue;
            }
            if (choice == 5){
                System.out.println("Đã thoát, bấm phím bất kì để thoát khỏi cửa sổ");
                break;
            }
            String x = "";
            String y = "";
            String result = "";
            //Đọc dữ liệu từ file có sẵn, nếu có. Nếu không thực hiện nhập như bình thường
            System.out.print("Bạn có muốn nhập dữ liệu đầu vào từ file('yes' or 'no'): ");
            String traloi = System.console().readLine();;
            if( traloi.equals("yes")){
                System.out.print("Vui lòng nhập địa chỉ file: ");
                String dia_chi_file = System.console().readLine();
                try (BufferedReader br = new BufferedReader(new FileReader(dia_chi_file))) {
                    x = br.readLine();
                    y = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Không thể đọc file input");
                    continue;
                }
            }
            //Nếu không thực hiện nhập như bình thường
            else if (traloi.equals("no")){
                System.out.print("Nhập số thứ nhất: ");
                x = System.console().readLine();
                System.out.print("Nhập số thứ hai: ");
                y = System.console().readLine();
            }
            else {
                System.out.println("Lựa chọn không hợp lệ");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("--- Phép cộng ---");
                    int[] sum = BigInt.addBigInt(BigInt.convertToBigInt(x), BigInt.convertToBigInt(y));
                    System.out.print("Kết quả là: ");
                    BigInt.printBigInt(sum);
                    result = BigInt.convertToString(sum);
                    writeInOutput(x, y, result);
                    System.out.println();
                    System.out.println();
                    break;
                case 2:
                    System.out.println("--- Phép trừ ---");
                    int[] sub = BigInt.subBigInt(BigInt.convertToBigInt(x), BigInt.convertToBigInt(y));
                    System.out.print("Kết quả là: ");
                    BigInt.printBigInt(sub);
                    result = BigInt.convertToString(sub);
                    writeInOutput(x, y, result);
                    System.out.println();
                    System.out.println();
                    break;
                case 3:
                    System.out.println("--- Phép nhân ---");
                    int[] mul = BigInt.multiBigInt(BigInt.convertToBigInt(x), BigInt.convertToBigInt(y));
                    System.out.print("Kết quả là: ");
                    BigInt.printBigInt(mul);
                    result = BigInt.convertToString(mul);
                    writeInOutput(x, y, result);
                    System.out.println();
                    System.out.println();
                    break;
                case 4:
                    System.out.println("--- Phép chia ---");
                    int[] div = BigInt.divideBigInt(BigInt.convertToBigInt(x), BigInt.convertToBigInt(y));
                    System.out.print("Kết quả là: ");
                    BigInt.printBigInt(div);
                    result = BigInt.convertToString(div);
                    writeInOutput(x, y, result);
                    System.out.println();
                    System.out.println();
                    break;
            }
        }
    }
}
