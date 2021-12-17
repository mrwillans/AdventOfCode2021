package Day17;

public class Day17 {

    void part1() {
        int ymin = -189;
        int ymax = -148;
        int xmax = 70;
        int xmin = 48;

        int peakY = Integer.MIN_VALUE;
        long count = 0;

        for (int x = 0; x < 500; x++) {
            for (int y = -500; y < 500; y++) {
                int peakYLocal = Integer.MIN_VALUE;
                int xvel = x;
                int yvel = y;
                int xpos = 0;
                int ypos = 0;

                for (int step = 0; step < 1000; step++) {
                    if (xmin <= xpos && xpos <= xmax && ymin <= ypos && ypos <= ymax) {
                        count++;
                        if (peakYLocal > peakY) {
                            peakY = peakYLocal;
                        }
                        break;
                    }
                    xpos = xpos + xvel;
                    ypos = ypos + yvel;
                    if (ypos > peakYLocal) {
                        peakYLocal = ypos;
                    }
                    if (xvel > 0) {
                        xvel--;
                    }
                    yvel--;
                }
            }
        }
        System.out.println(peakY);
        System.out.println(count);
    }

    public static void main(String[] args) {
        Day17 day17 = new Day17();
        day17.part1();
    }


}
