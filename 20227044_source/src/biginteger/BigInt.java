// Name: Nguyễn Trường Giang
// Student ID: 20227044
// Class: MI1 - K67 - 150327
// Project: 02 - Xây dựng thư viện phép toán với sô nguyên lớn
// Date: 19/6/2024

package biginteger;

public class BigInt {
    //Phương thức chuyển kí tự '-' đại diện cho dấu âm thành kí tự '0' nếu có và thêm kí tự '1' nếu là chuỗi số dương
    public static String changeSign(String input) {
        if (input.charAt(0) == '-'){
            input = '0' + input.substring(1);
        }
        else {
            input = '1' + input;
        }
        return input;
    }

    //Hàm khử các số 0 ở đầu số mà không có ý nghĩa
    public static int[] removeZero(int[] input) {
        int count = 0;
        int length_input = input.length;
        for (int i = length_input - 1; i > 1; i--) {
            if (input[i]!= 0) {
                break;
            }
            count++;
        }
        if (count != 0) {
            int[] result = new int[length_input - count];
            int length_result = result.length;
            for (int i = 0; i < length_result; i++) {
                result[i] = input[i];
            }
            return result;
        }
        else {
            return input;
        }
    }

    /* MÔ tả:
        Hàm BigInteger chuyển đổi từ một chuỗi kí tự sang một mảng số nguyên theo thứ tự ngược lại.
        Chuỗi kí tự phải là số nguyên từ 0 tới 9 nếu số nguyên là dương.
        Khử các chữ số không có nghĩa.
        Chuỗi kí tự không hợp lệ thì trả về null.
        Kết quả nhận được là một mảng chứa các số nguyên, với phần tử đầu tiên kí hiệu cho dấu 0 nếu là âm và 1 nếu là dương.
        Số 0 mặc định là số nguyên dương. */
    public static int[] convertToBigInt(String input) {
        input = changeSign(input);
        int length_input = input.length();
        
        //Khởi tạo một mảng để chứa các chữ số với phần tử đầu tiên lưu thông tin về dấu
        int[] bi = new int[length_input];
        bi[0] = Character.getNumericValue(input.charAt(0));

        //Nếu số nguyên khác 0, kiểm tra xem số nhập vào đã đúng chưa, nếu đúng thì truyền các chữ số vào mảng từ vị trí 1 tới độ dài số - 1
        int j = 1;
        for (int i = length_input - 1; i > 0; i--) {
            char kitu = input.charAt(i);
            if (kitu < '0' || kitu > '9') {
                System.out.println("Sai định dạng");
                return null;
            }
            else {
                bi[j] = Character.getNumericValue(kitu);
                j++;
            }
        }

        bi = removeZero(bi);

        return bi;
    }

    /*
     Chuyển số nguyên lớn về dạng chuỗi
     */
    public static String convertToString(int[] bi) {
        String s = "";
        if (bi[1] == -1) {
            s = "ERROR";
            return s;
        }
        if (bi[0] == 0) {
            s = "-";
            for (int i = bi.length - 1; i > 0;i--) {
                s = s + bi[i];
            }
            return s;
        }
        if (bi[0] == 1) {
            for (int i = bi.length - 1; i > 0;i--) {
                s = s + bi[i];
            }
        }
        return s;
    }

    // hàm in số nguyên lớn ra màn hình
    public static int printBigInt(int[] bi) {
        System.out.println(bi);
        return 0;
    }
    

    /* Hàm lấy trị tuyệt đối của số được nhập vào
    Kết quả trả về là một mảng phần tử đầu tiên đại diện dấu các số được sắp xếp ngược với số ngoài thực tế*/
    public static int[] getAbsBigInt(int[] a) {
            int[] result = new int[a.length];
            for (int i = 1; i < a.length; i++) {
                result[i] = a[i];
            }
            result[0] = 1; 
            return result;
    }
    /* Phép so sánh 2 số nguyên dương với a là số trước và b là số sau
        Nếu số a lớn hơn số b hàm trả về số nguyên 1
        Nếu số a nhỏ hơn số b hàm trả về số nguyên -1
        Nếu số a bằng số b hàm trả về số nguyên 0
    */
     public static int comparePosBigInt(int[] a, int[] b) {
        int length_a = a.length;
        int length_b = b.length;
        
        //So sánh độ dài của 2 số nguyên dương, số có độ dài lớn hơn thì lớn hơn
        //Nếu độ dài bằng nhau xét từng chữ số từ trái sang phải trong thực tế và từ phải sang trái trong mảng
        if (length_a > length_b) {
            return 1;
        }
        else if (length_a < length_b) {
            return -1;
        }
        else {
            for (int i = length_a - 1; i >= 1; i--) {
                if (a[i] > b[i]) {
                    return 1;
                }
                if (a[i] < b[i]) {
                    return -1;
                }
            }
        }

        return 0;
    }
    /*
    phép so sánh 2 số nguyên không phân biệt dấu với số a là số trước và b là số sau
        Nếu số a lớn hơn số b hàm trả về số nguyên 1
        Nếu số a nhỏ hơn số b hàm trả về số nguyên -1
        Nếu số a bằng số b hàm trả về số nguyên 0
    */
    public static int compareBigInt(int[] a, int[] b) {
        //xét các dấu của số, số dương thì lớn hơn số âm, nếu 2 số đều âm thì so sánh giá trị tuyệt đối của 2 số đảo lại dấu
        if (a[0] == 1 && b[0] == 0) {
            return 1;
        }
        else if (a[0] == 0 && b[0] == 1) {
            return -1;
        }
        else if (a[0] == 1 && b[0] == 1) {
            return BigInt.comparePosBigInt(a, b);
        }
        else {
            return -1 * BigInt.comparePosBigInt(getAbsBigInt(a), getAbsBigInt(b));
        }

    }

    /*Cân băng độ dài của số b với số a với độ dài a ban đầu nhỏ hơn độ dài b
    Thêm các số không vào b
     Trả về mảng lưu thông tin của số b đã cân bằng
    */
    public static int[] balanceLengthBigInt(int[] a, int[] b) {
        int length_a = a.length;
        int length_b = b.length;
        int[] b_temp = new int[length_a];
        for (int i = 0; i < length_b; i++)
            {
                b_temp[i] = b[i];
            }
            for (int i = length_b; i < length_a; i++)
            {
                b_temp[i] = 0;
            }
        return b_temp;
    }

    /*
     Hàm tính tổng 2 số nguyên dương cùng độ dài
     Kết quả có độ dài tối đa là độ dài của số a thêm 1, tối thiểu là độ dài của a, kết quả là 1 số dương
     Kết quả trả về là một mảng phần tử đầu tiên đại diện dấu các số được sắp xếp ngược với số ngoài thực tế
    */
    public static int[] addPosSameBigInt(int[] a, int[] b) {
        int length_a = a.length;
        // Khởi tạo mảng kết quả có độ dài lớn nhất có thể
        int[] result = new int[length_a + 1];
        result[0] = 1;

        // Thực hiện cộng các chữ số lần lượt từ trái qua phải trong mảng tương ứng với từ phải qua trái trong thực tế. 
        // Code được triển khai giống với cách cộng 2 số phổ thông
        result[1] = 0;
        result[length_a] = 0;
        for (int i = 1; i < length_a; i++) {
            int temp = a[i] + b[i];
            result[i] = result[i] + temp % 10;
            result[i + 1] = temp / 10;
        }
        result = removeZero(result);
        return result;
    }

    /*
    Hàm tính tổng 2 số nguyên dương
    đưa các dạng 2 số nguyên có độ dài không bằng nhau về dạng bằng nhau
    Sử dụng hàm tính 2 số nguyên dương có độ dài bằng nhau
    Kết quả trả về là một mảng phần tử đầu tiên đại diện dấu các số được sắp xếp ngược với số ngoài thực tế
    */
    public static int[] addPosBigInt(int[] a, int[] b) {   
        int length_a = a.length;
        int length_b = b.length;
        int[] result = new int[length_a + 1];

        if (length_a > length_b) {
            int[] b_temp = balanceLengthBigInt(a, b);
            result = addPosSameBigInt(a, b_temp);
        }
        else if (length_a == length_b) {
            result = addPosSameBigInt(a, b);
        }
        else {
            int[] a_temp = balanceLengthBigInt(b, a);
            result = addPosSameBigInt(a_temp, b);
        }
    return result;
    }

    /* 
     Hàm tính hiệu 2 số nguyên dương cùng độ dài, số trước >= số sau
     Xử dụng kĩ thuật cân bằng chữ số bằng cách thêm số 0 để được 2 số có độ dài bằng nhau
     Kết quả trả về là một mảng phần tử đầu tiên đại diện dấu các số được sắp xếp ngược với số ngoài thực tế
    */
    public static int[] subPosSameBigInt(int[] a, int[] b) {
        int length_a = a.length;
        int[] result = new int[a.length];
        result[0] = 1;
        for (int i = 1; i < length_a; i++)
        {
            if (a[i] >= b[i])
            {
                result[i] = a[i] - b[i];
            }
            else
            {
                result[i] = 10 + a[i] - b[i];
                if (i == length_a - 1) break;
                b[i + 1]++;
            }
        }
        result = removeZero(result);
        return result;
    }
    /*
    Hàm tính hiệu 2 số nguyên dương
    Kết quả trả về là một mảng phần tử đầu tiên đại diện dấu các số được sắp xếp ngược với số ngoài thực tế 
    */
    public static int[] subPosBigInt(int[] a, int[] b) {
        int length_a = a.length;
        int length_b = b.length;
        if (length_a < length_b){
            int[] a_temp = balanceLengthBigInt(b, a);
            int[] result = BigInt.subPosSameBigInt(b, a_temp);
            result[0] = 0;
            return result;
        }
        else if (length_a == length_b){   
            if (BigInt.compareBigInt(a, b) == 1) {
                return BigInt.subPosSameBigInt(a, b);
            }
            else if (BigInt.compareBigInt(a, b) == -1) {
                int[] result = BigInt.subPosSameBigInt(b, a);
                result[0] = 0;
                return result;
            }
            else {
                return BigInt.subPosSameBigInt(a, b);
            }
        }
        else {
            int[] b_temp = balanceLengthBigInt(a, b);
            return BigInt.subPosSameBigInt(a, b_temp);
        }
    }

    /*
    Hàm tính tổng 2 số nguyên bất kì
    Áp dụng tính chất của dấu cộng trừ biến đổi lại bằng thành các phép toán công trừ 2 số dương rồi tiến hành đổi dấu cho phù hợp
    Kết quả trả về là một mảng phần tử đầu tiên đại diện dấu các số được sắp xếp ngược với số ngoài thực tế
    */
    public static int[] addBigInt(int[] a, int[] b) {
        if (a[0] == 1 && b[0] == 1) {
            return BigInt.addPosBigInt(a, b);
        }
        else if (a[0] == 0 && b[0] == 0) {   
            int[] result_temp = BigInt.addPosBigInt(BigInt.getAbsBigInt(a), BigInt.getAbsBigInt(b));
            result_temp[0] = 0;
            return result_temp;
        }
        else if (a[0] == 1 && b[0] == 0) {
            
            return BigInt.subPosBigInt(a, BigInt.getAbsBigInt(b));
        }
        else {
            return BigInt.subPosBigInt(b, BigInt.getAbsBigInt(a));
        }
    }

    /*
    Hàm tính hiệu 2 số nguyên bất kì
    Áp dụng tính chất của dấu cộng trừ biến đổi lại bằng thành các phép toán công trừ 2 số dương rồi tiến hành đổi dấu cho phù hợp
    Kết quả trả về là một mảng phần tử đầu tiên đại diện dấu các số được sắp xếp ngược với số ngoài thực tế
    */
    public static int[] subBigInt(int[] a, int[] b) {
        if (compareBigInt(a, b) == 0) {
            int[] result = new int[] {1,0};
            return result; 
        }
        if (a[0] == 1 && b[0] == 1) {
            return BigInt.subPosBigInt(a, b);
        }
        else if (a[0] == 0 && b[0] == 0) {
            return BigInt.subPosBigInt(getAbsBigInt(b), getAbsBigInt(a));
        }
        else if (a[0] == 1 && b[0] == 0) {
            return BigInt.addPosBigInt(a, BigInt.getAbsBigInt(b));
        }
        else {
            int[] result_temp = BigInt.addBigInt(BigInt.getAbsBigInt(a), BigInt.getAbsBigInt(b));
            result_temp[0] = 0;
            return result_temp;
        }
    }

    /*
    Hàm nhân 1 số nguyên dương với một số nhỏ hơn 10
    Tiến hành nhân như cách phổ thông
    Kết quả được số có độ dài tối đa có thể là length_a + length_b + 1 
    Thêm một vị trí để lưu thông tin dấu => cần tối đa length_a + length_b + 1
    Kết quả trả về là một mảng phần tử đầu tiên đại diện dấu các số được sắp xếp ngược với số ngoài thực tế
    */
    public static int[] multiPosBigInt1(int[] a, int b) {   
        
        int length_a = a.length;
        int[] result = new int[length_a + 2];
        if (b == 0) {
            result = new int[2];
            result[0] = 1;
            result[1] = 0;
            return result;
        }
        //Cho mảng kết quả ban đầu mang giá trị 0
        result[0] = 1;
        int length_result = result.length;
        for (int i = 1; i < length_result; i++) {
            result[i] = 0;
        }
        //Tiến hành nhân số b với lần lượt các chữ số trong a và cộng vào phần dư ra từ lượt nhân trước
        int temp = 0;
        for (int i = 1; i < a.length; i++) {
            temp = result[i];
            result[i] = (temp + (a[i] * b)) % 10;
            result[i + 1] = result[i + 1] + (temp + (a[i] * b)) / 10;
        }

        result = removeZero(result);
        return result;
    }
    /*
    Hàm nhân 1 số nguyên dương với một số nguyên dương
    Tiến hành nhân như cách phổ thông
    Kết quả được số có độ dài tối đa có thể là length_a + length_b + 1 
    Thêm một vị trí để lưu thông tin dấu => cần tối đa length_a + length_b + 1
    Kết quả trả về là một mảng phần tử đầu tiên đại diện dấu các số được sắp xếp ngược với số ngoài thực tế
    */
    public static int[] multiPosBigInt(int[] a, int[] b) {
        int length_a = a.length;
        int length_b = b.length;
        int length_result = length_a + length_b + 1;
        int[] result = new int[length_result];

        //Cho mảng kết quả ban đầu mang giá trị 0
        result[0] = 1;
        for (int i = 1; i < length_result; i++) {
            result[i] = 0;
        }

        //Tiến hành nhân mỗi chữ số b với số a sau đó cộng vào kết quả, mỗi lần tiến hành cộng lùi lên trước vào kết quả 1 hàng đơn vị
        int count = 1;
        int temp_int = 0;
        for (int i = 1; i < length_b; i++) {
            int[] temp = BigInt.multiPosBigInt1(a, b[i]);
            for (int j = 1, k = count;j < temp.length; j++,k++) {
                temp_int = result[k];
                result[k] = (temp_int + temp[j]) % 10;
                result[k + 1] = result[k + 1] + (temp_int + temp[j]) / 10;
            }
            count++;
        }
        
        result = removeZero(result);
        return result;
    }
    /*
    Hàm nhân 2 số nguyên bất kì
    Sử dụng các hàm nhân 2 số nguyên dương a,b đã được xây dựng, kết hợp các tính chất của dấu để biến đổi 
    Kết quả trả về là một mảng phần tử đầu tiên đại diện dấu các số được sắp xếp ngược với số ngoài thực tế
    */
    public static int[] multiBigInt(int[] a, int[] b) {
        if ((b.length == 2 && b[1] == 0) || (a.length == 2 && a[1] == 0)) {
            int[] result = new int[2];
            result[0] = 1;
            result[1] = 0;
            return result;
        }
        if (a[0] == 1 && b[0] == 1) {
            return BigInt.multiPosBigInt(a, b);
        }
        else if (a[0] == 0 && b[0] == 0) {
            return BigInt.multiPosBigInt(BigInt.getAbsBigInt(a), BigInt.getAbsBigInt(b));
        }
        else if (a[0] == 1 && b[0] == 0) {
            int[] result = BigInt.multiPosBigInt(a, BigInt.getAbsBigInt(b));
            result[0] = 0;
            return result;
        }
        else {
            int[] result = BigInt.multiPosBigInt(b, BigInt.getAbsBigInt(a));
            result[0] = 0;
            return result;
        }
    }

    /*
    Hàm chia lấy nguyên số dương a cho số dương b với b != 0
    Ta chia lần lượt như cách chia phổ thông, lấy từng số của a bắt đầu từ lớp lớn nhất chia cho số b
    lưu từng kết quả vào một mảng tạm thời sau đó chuyển lại theo dạng đã quy ước
    Kết quả trả về là một mảng phần tử đầu tiên đại diện dấu các số được sắp xếp ngược với số ngoài thực tế
    */
    public static int[] dividePosBigInt(int[] a, int[] b) {
        int length_a = a.length;

        //khởi tạo mảng thành phần của a để lưu thông tin về lấy thành phần chia lần đầu của a
        int[] ingredient_a = new int[2];
        ingredient_a[0] = 1;
        ingredient_a[1] = a[length_a - 1];
        //Mảng lưu thông tin số dư của phép chia thành phần
        int[] surplus = new int[2];
        //Mảng kết quả tạm
        int[] result_temp = new int[length_a];
        //Biến đếm để kiểm soát vị trí lưu thành phần kết quả vào mảng kết quả tạm
        int count = 1;
        //Mảng tạm lưu thông tin sau khi nhân thành phần kết quả thu được với số b
        int [] temp = new int[1];

        //Lấy thành phần kết quả của mỗi lần chia thành phần
        for(int i = length_a - 1; i > 0;i--){
            for (int j = 0; j <= 10; j++) {
                temp = multiPosBigInt1(b, j);
                if (compareBigInt(ingredient_a,temp) == 0) {
                    result_temp[count] = j;
                    temp = multiPosBigInt1(b, result_temp[count]);
                }
                else if (compareBigInt(temp,ingredient_a) == 1) {
                    result_temp[count] = j - 1;
                    temp =multiPosBigInt1(b, result_temp[count]);
                }
                else {
                    continue;
                }
                count++;
                break;
            }

            //Gán thành phần chia tiếp theo
            surplus = subBigInt(ingredient_a, temp);
            int [] temp_3 = new int [surplus.length + 1];
            temp_3[0] = 1;
            temp_3[1] = a[i - 1];
            for (int j = 2, k = 1; j < temp_3.length; j++,k++) {
                temp_3[j] = surplus[k];
            }
            ingredient_a = removeZero(temp_3);
        }

        //Chuyển mảng kết quả tạm thành mảng kết quả theo quy ước ban đầu
        int[] result = new int[length_a];
        result[0] = 1;
        for (int i = 1; i < length_a; i++) {
            result[i] = result_temp[length_a - i];
        }
        result = removeZero(result);
        return result;
    }

    /*
    Hàm chia 2 số nguyên bất kì cho nhau
    Sử dụng các hàm chia 2 số nguyên dương a,b đã được xây dựng, kết hợp các tính chất của dấu để biến đổi 
    Kết quả trả về là một mảng phần tử đầu tiên đại diện dấu các số được sắp xếp ngược với số ngoài thực tế
    */
    public static int[] divideBigInt(int[] a, int[] b) {
        //Nếu b == 0 trì tạo một bảng có đọ dài 2 số thứ nhất là -1 để đánh đấu là lỗi
        if (b.length == 2 && b[1] == 0) {
            return new int[]{1,-1};
        }

        //Nếu số a nhỏ hơn b hoặc a == 0 thì kết quả là một mảng tương đương với giá trị 0
        if (compareBigInt(getAbsBigInt(a), getAbsBigInt(b)) == -1 || (a.length == 2 && a[1] == 0)) {
            int[] result = new int[2];
            result[0] = 1;
            result[1] = 0;
            return result;
        }

        //Số a dương và b dương
        if (a[0] == 1 && b[0] == 1) {
            int[] result = BigInt.dividePosBigInt(a, b);
            return result;
        }

        //Số a âm và b âm
        else if (a[0] == 0 && b[0] == 0) {
            int[] result = BigInt.dividePosBigInt(BigInt.getAbsBigInt(a), BigInt.getAbsBigInt(b));
            return result;
        }

        //Số a dương và b âm
        else if (a[0] == 1 && b[0] == 0) {
            int[] result = BigInt.dividePosBigInt(a, BigInt.getAbsBigInt(b));
            result[0] = 0;
            return result;
        }
        
        //Số a âm và b dương
        else {
            int[] result = BigInt.dividePosBigInt(BigInt.getAbsBigInt(a), b);
            result[0] = 0;
            return result;
        }
    }
}
