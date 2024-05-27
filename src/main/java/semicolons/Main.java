package semicolons;

public class Main {
    public static void main(String[] args) {

            // A recursive function used by countWays


                int n = 4;
                System.out.println(countWays(n));
//        IntStream.of(1,1,3,3,7,6,7)
//                .distinct()
//                .parallel()
//                .map(i -> i+2)
//                .sequential()
//                .forEach(System.out::print);

//        Optional.of(24)
//                .filter(v -> v%4 ==0)
//                .map(v -> {
//                    System.out.println(v); return v/2;
//                })
//                .filter(s -> s!=0)
//                .map(s -> s/6)
//                .map(s -> {
//                    System.out.println(s);
//                    return s;
//                }).orElseThrow(() -> new Exception(""));

//        System.out.println(List.of("MM").containsAll(Arrays.asList("MM")));
    }
    public static int countWays(int n)
    {
        int[] res = new int[n + 1];
        res[0] = 1;
        res[1] = 1;
        res[2] = 1;
        res[3] = 1;
        System.out.println(res[0] +", "+ res[1] +", "+ res[2]+", "+ res[3]);
        for (int i = 3; i <= n; i++) {
            res[i] = res[i - 1] + res[i - 2] + res[i - 3];
            System.out.println(res[i - 1] +", "+ res[i - 2] +", "+ res[i - 3]);
        }
        return res[n];
    }
}