import java.util.Arrays;
 
public class GroupStage{
    //games是组内的六局比赛 比如“12”就代表左边队伍1 vs 右边队伍2
    static String[] games = {"12", "13", "14", "23", "24", "34"};
    //results模拟比赛结果 0代表左边队伍输了 假设一开始的结果是左边队伍六局全输
    static String results = "000000";
    
    //这个函数是不停枚举每一种比赛结果 也就是729种
    private static boolean nextResult(){
        //从000000每次自增1 直到222222
        if(results.equals("222222")) return false;
        //直接把结果转化成3进制 并且每次直接+1 就可以遍历000000-222222
        int res = Integer.parseInt(results, 3) + 1;
        //再把数字转换成字符串给results
        results = Integer.toString(res, 3);
        //转换成数字会丢失左边的0 这个while循环可以把左边的0补齐
        while(results.length() < 6) results = "0" + results;	
        //true表示循环没有结束 还有没遍历的结果
        return true;
        
    }
 
    public static void main(String[] args){
        //points是最后的结果集 4表示4个队伍的1-4排名 0-9 表示队伍获得的积分
        int[][] points = new int[4][10]; 		
        do{
             //记录每个队的实时积分
            int[] records = {0,0,0,0};
           //模拟6场比赛的结果
            for(int i = 0; i < 6; i++){
                //检查结果集的每一位 代表每场比赛的结果
                switch(results.charAt(i)){
                	//2 代表左边胜利 这里records数组下标从0开始 但是games的队伍名从1开始 所以每次是减一正好可以对应records的下标 然后胜利的（左边的）队伍加3分
                    case '2': records[games[i].charAt(0) - '1'] += 3; break;    
                    //1 代表平局 左右两个队伍都要加1分
                    case '1':                                                   
                        records[games[i].charAt(0) - '1']++;
                        records[games[i].charAt(1) - '1']++;
                        break;
                    //0 代表左边输了 也就是右边队伍（下标是1）加3分
                    case '0': records[games[i].charAt(1) - '1'] += 3; break;    
                        
                }
            }
            //把积分按照从小到大排序 分最高的在最后
            Arrays.sort(records);	
            //第四名的对应的积分 计数+1
            points[0][records[0]]++;
            points[1][records[1]]++;
            points[2][records[2]]++;
            points[3][records[3]]++;
        }while(nextResult());//枚举下一种比赛结果
        //输出获得第一名的每个积分的计数情况
        System.out.println("First place: " + Arrays.toString(points[3]));
        System.out.println("Second place: " + Arrays.toString(points[2]));
        System.out.println("Third place: " + Arrays.toString(points[1]));
        System.out.println("Fourth place: " + Arrays.toString(points[0]));
    }
}