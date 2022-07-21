package com.studentGUI;

public class MyDate {
        int y;
        int m;
        int d;
        public static final int MIN_YEAR = 1900;
        MyDate(){
            this(1970,1,1);
        }
        MyDate(int y,int m,int d){
            this.y = y < MIN_YEAR ? MIN_YEAR : y;
            this.m = m;
            this.d = d;
        }
        MyDate(MyDate date){
            this.y = date.getY();
            this.m = date.getM();
            this.d = date.getD();
        }

    @Override
    public String toString() {
        return "MyDate{" +
                "y=" + y +
                ", m=" + m +
                ", d=" + d +
                '}';
    }

    public int getD() {
        return d;
    }

    public int getM() {
        return m;
    }

    public int getY() {
        return y;

    }

    public void setD(int d) {
        this.d = d;
        System.out.println(d);
    }

    public void setM(int m) {
        this.m = m;
        System.out.println(m);
    }

    public void setY(int y) {
        this.y = y;
        System.out.println(y);
    }

    /**
     * 输入日期大于 自己 return 1
     * = return 0
     * < return -1
     * @param date
     * @return
     */
    public int myEquals(MyDate date){
            if (date.getY() != this.y){
                return date.getY() > this.y ? -1 : 1 ;
            }else if (date.getM() != this.m){
                return date.getM() > this.m ? -1 : 1;
            }else if (date.getD() != this.d){
                return date.getD() > this.d ? -1 : 1;
            }
            return 0;
    }

    public static void main(String[] args) {
        MyDate a = new MyDate(2000,5,6);
        MyDate b = new MyDate(1999,5,6);
        MyDate c = new MyDate(2000,5,6);
        System.out.print(a.equals(b));

        System.out.print(a.equals(c));
        System.out.print(b.equals(c));
    }
}
