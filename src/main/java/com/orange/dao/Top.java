package com.orange.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.orange.pojo.Post;
import com.orange.pojo.Vote;
import com.orange.utils.MapValueComparator;

import java.util.*;

public class Top {
    private VoteDao voteDao;
    private PostDao postDao;

    public Top() {
        voteDao = new VoteDaoImpl();
        postDao = new PostDaoImpl();
    }
    public JSON getTop() {
        List<Post> postList = new ArrayList<>();
        postList = postDao.getCount();


        List<Vote> voteList = new ArrayList<>();
        voteList = voteDao.getVote();

        Map<Integer, Integer> map = new HashMap<>();

        for (Post post : postList) {
            for (Vote vote : voteList) {
                if (post.getId() == vote.getPost_id()) {
                    map.put(post.getId(), ((7 * post.getComment_count()) + 3 * vote.getVote()));
                }
            }
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println("Key = " + entry.getKey() + " Value = " + entry.getValue());

        }

        Map<Integer, Integer> sortedMap = new LinkedHashMap<>();
        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<Map.Entry<Integer, Integer>>(map.entrySet());
        Collections.sort(entryList, new MapValueComparator());
        Iterator<Map.Entry<Integer, Integer>> iterator = entryList.iterator();
        Map.Entry<Integer,Integer> temEntry = null;
        while (iterator.hasNext()) {
            temEntry = iterator.next();
            sortedMap.put(temEntry.getKey(), temEntry.getValue());
        }

        System.out.println("---------------Sorted--------------");

        for (Map.Entry<Integer, Integer> entry : sortedMap.entrySet()) {
            System.out.println("Key = " + entry.getKey() + " Value = " + entry.getValue());
        }

        int topNum = 5;/*front Post*/
        int num[] = new int[topNum];
        String title[] = new String[topNum];
        int i = 0;
        Iterator<Map.Entry<Integer, Integer>> entryIterator = sortedMap.entrySet().iterator();
        while (i < 5) {
            Map.Entry<Integer, Integer> entry = entryIterator.next();
            num[i] = entry.getKey();
            i++;
        }

        int k = 0;
        for (int j = 0; j < topNum; j++) {
            for (Post post : postList) {
                if (num[j] == post.getId()) {
                    title[k] = post.getTitle();
                    k++;
                }
            }
        }

        for (String s : title) {
            System.out.println(s);
        }

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("id", num);
        resultMap.put("title", title);
        JSONObject jsonObject = new JSONObject(resultMap);
        System.out.println(jsonObject);

        return jsonObject;
    }
}
