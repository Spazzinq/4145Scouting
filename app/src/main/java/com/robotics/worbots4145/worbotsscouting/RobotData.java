package com.robotics.worbots4145.worbotsscouting;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RobotData {
    public ArrayList<String> botArray;
    public String data;
    public ArrayList<ArrayList<String>> matches;
    public RobotData() {
        data = "60\n" +
                "117\n" +
                "128\n" +
                "144\n" +
                "279\n" +
                "325\n" +
                "329\n" +
                "340\n" +
                "379\n" +
                "554\n" +
                "677\n" +
                "695\n" +
                "706\n" +
                "1014\n" +
                "1038\n" +
                "1317\n" +
                "1507\n" +
                "1511\n" +
                "1559\n" +
                "1736\n" +
                "1787\n" +
                "2081\n" +
                "2252\n" +
                "2603\n" +
                "2783\n" +
                "3138\n" +
                "3173\n" +
                "3193\n" +
                "3201\n" +
                "3266\n" +
                "3324\n" +
                "3504\n" +
                "3777\n" +
                "3814\n" +
                "4027\n" +
                "4028\n" +
                "4085\n" +
                "4145\n" +
                "4269\n" +
                "4283\n" +
                "4284\n" +
                "4467\n" +
                "4521\n" +
                "4611\n" +
                "4930\n" +
                "4991\n" +
                "5413\n" +
                "5492\n" +
                "5667\n" +
                "5811\n" +
                "6032\n" +
                "6181\n" +
                "6834\n" +
                "6927\n" +
                "6936\n" +
                "7434\n" +
                "7460\n" +
                "7486\n" +
                "7515\n" +
                "8027\n" +
                "8289\n" +
                "90\n" +
                "3777\t3201\t3173\t4085\t6181\t3504\n" +
                "5492\t706\t1317\t6834\t279\t1038\n" +
                "117\t677\t5413\t7434\t2603\t4284\n" +
                "4027\t4611\t4283\t4028\t6032\t1787\n" +
                "2081\t3193\t1511\t3324\t4467\t329\n" +
                "695\t1014\t3814\t7486\t2783\t2252\n" +
                "3266\t340\t4145\t4269\t4521\t5811\n" +
                "6936\t6927\t8027\t8289\t128\t5667\n" +
                "325\t554\t1736\t3138\t1507\t7460\n" +
                "379\t4930\t1559\t144\t7515\t4991\n" +
                "3193\t706\t6181\t7434\t7486\t2081\n" +
                "4085\t340\t1787\t2603\t1014\t1511\n" +
                "5811\t6032\t279\t4028\t4284\t128\n" +
                "3138\t1317\t329\t3814\t3777\t677\n" +
                "7515\t3504\t325\t4991\t1038\t6936\n" +
                "4283\t7460\t5413\t5492\t4145\t1559\n" +
                "4269\t3173\t6834\t4027\t2783\t1507\n" +
                "554\t4467\t4611\t144\t8027\t5667\n" +
                "695\t8289\t2252\t4930\t4521\t1736\n" +
                "117\t3266\t3324\t6927\t3201\t379\n" +
                "1317\t1559\t5811\t325\t6936\t7434\n" +
                "4991\t128\t5413\t3173\t1787\t3138\n" +
                "5667\t677\t554\t279\t4085\t4269\n" +
                "1511\t6181\t4521\t8027\t706\t4283\n" +
                "8289\t329\t2783\t4145\t3324\t379\n" +
                "7515\t2081\t117\t6834\t1014\t3266\n" +
                "1507\t2603\t3777\t7460\t695\t3193\n" +
                "340\t4028\t1038\t1736\t144\t4284\n" +
                "6032\t4027\t3201\t2252\t5492\t4467\n" +
                "3814\t7486\t4611\t3504\t4930\t6927\n" +
                "2783\t7515\t4283\t128\t279\t117\n" +
                "677\t1787\t6181\t4145\t6936\t6834\n" +
                "1014\t3173\t3193\t8027\t1317\t8289\n" +
                "329\t2252\t7460\t4085\t3266\t4028\n" +
                "4611\t4991\t3138\t5492\t1507\t7434\n" +
                "3324\t4930\t325\t5413\t6032\t340\n" +
                "706\t1736\t4269\t4467\t3814\t2603\n" +
                "6927\t4284\t2081\t3504\t4521\t554\n" +
                "379\t1038\t7486\t1559\t4027\t3777\n" +
                "1511\t5811\t144\t5667\t695\t3201\n" +
                "7460\t6834\t4085\t4611\t5413\t4930\n" +
                "4269\t1507\t117\t706\t325\t8289\n" +
                "4284\t2252\t1787\t3193\t1317\t3324\n" +
                "8027\t3777\t4991\t2783\t677\t2081\n" +
                "1038\t6927\t4467\t4283\t5811\t1014\n" +
                "4521\t6936\t279\t3201\t3138\t7486\n" +
                "1511\t4028\t1559\t6181\t379\t3814\n" +
                "2603\t3266\t5492\t144\t554\t128\n" +
                "3504\t7434\t4027\t340\t329\t5667\n" +
                "6032\t1736\t695\t4145\t7515\t3173\n" +
                "1787\t5413\t4269\t1038\t8027\t4521\n" +
                "3193\t5811\t2783\t4930\t3138\t4085\n" +
                "7486\t8289\t144\t677\t1511\t6834\n" +
                "3777\t3324\t1014\t554\t7434\t4028\n" +
                "6181\t7460\t4467\t117\t3504\t1317\n" +
                "6936\t379\t3266\t1736\t4283\t3173\n" +
                "329\t3814\t6032\t2603\t4991\t279\n" +
                "7515\t3201\t128\t4611\t340\t706\n" +
                "1507\t5667\t4145\t2081\t1559\t2252\n" +
                "325\t6927\t695\t4027\t5492\t4284\n" +
                "554\t7486\t3777\t1787\t117\t5811\n" +
                "4467\t7434\t3266\t8289\t4085\t1038\n" +
                "3324\t7460\t340\t3173\t3814\t6936\n" +
                "4521\t4611\t2783\t6181\t1736\t329\n" +
                "1507\t4028\t7515\t1317\t1511\t6927\n" +
                "279\t144\t3504\t5413\t3193\t4027\n" +
                "4284\t8027\t1014\t4991\t4145\t3201\n" +
                "706\t5667\t6032\t3138\t695\t379\n" +
                "128\t2081\t5492\t677\t4269\t4930\n" +
                "2252\t6834\t2603\t1559\t4283\t325\n" +
                "340\t1736\t6927\t279\t3777\t3193\n" +
                "1038\t117\t4145\t3173\t1511\t4611\n" +
                "695\t4467\t706\t4028\t5413\t6936\n" +
                "677\t5492\t7460\t1014\t379\t4521\n" +
                "1559\t6834\t3504\t128\t3324\t3138\n" +
                "2252\t6181\t4991\t5811\t8289\t554\n" +
                "3814\t325\t1507\t3266\t5667\t1787\n" +
                "4284\t4283\t7486\t4269\t329\t7515\n" +
                "2783\t4085\t1317\t6032\t2081\t144\n" +
                "4930\t2603\t4027\t3201\t7434\t8027\n" +
                "4521\t3193\t4028\t6834\t4991\t117\n" +
                "3266\t279\t1559\t1787\t4611\t695\n" +
                "7486\t4145\t677\t4467\t325\t340\n" +
                "6936\t4284\t1511\t3777\t2783\t5492\n" +
                "379\t3504\t8027\t5811\t2252\t1507\n" +
                "4283\t128\t4085\t1317\t4027\t1736\n" +
                "329\t3173\t4930\t1014\t554\t706\n" +
                "2081\t3201\t8289\t5413\t3814\t7515\n" +
                "6927\t144\t7434\t7460\t4269\t6032\n" +
                "5667\t3138\t2603\t3324\t1038\t6181";
        int bot = 0;
        int numBots = 0;
        Scanner sc = new Scanner(data);
        if (sc.hasNextLine())
            numBots = sc.nextInt();

        botArray = new ArrayList<String>();
        for (int i = 0; i < numBots; i++) {
            bot = sc.nextInt();
            botArray.add(Integer.toString(bot));
        }

        int numMatches = 0;
        if(sc.hasNextLine())
            numMatches = sc.nextInt();

        matches = new ArrayList<ArrayList<String>>();
        for(int i = 0; i<numMatches; i++) {
            matches.add(new ArrayList<String>());
            for(int j = 0; j<6; j++){
                bot = sc.nextInt();
                matches.get(i).add(Integer.toString(bot));
            }
        }

        ArrayList<String> scout = new ArrayList<String>();
        for(int i = 0; i<26; i++)
            scout.add("");
        //FirebaseDatabase.getInstance().getReference().child("RobotData").removeValue();
        FirebaseDatabase.getInstance().getReference().child("MatchData").removeValue();
        FirebaseDatabase.getInstance().getReference().child("Schedule").removeValue();
        //FirebaseDatabase.getInstance().getReference().child("Bots").removeValue();
        //for(int i = 0; i<numBots; i++)
        //    FirebaseDatabase.getInstance().getReference().child("RobotData").child(botArray.get(i)).push().setValue(scout);
        FirebaseDatabase.getInstance().getReference().child("Schedule").push().setValue(matches);
        //FirebaseDatabase.getInstance().getReference().child("Bots").push().setValue(botArray);
        sc.close();
    }
}
