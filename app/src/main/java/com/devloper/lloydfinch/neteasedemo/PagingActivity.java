package com.devloper.lloydfinch.neteasedemo;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.arch.paging.PagedListAdapter;
import android.arch.paging.PositionalDataSource;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.recyclerview.extensions.AsyncDifferConfig;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 用来展示Paging组件
 */
public class PagingActivity extends AppCompatActivity {

    private static final String TAG = "PagingActivity";

    private RecyclerView recyclerView;

    /**
     * 创建一个DiffUtil.ItemCallback用来对数据进行新旧数据差分处理
     */
    private DiffUtil.ItemCallback<User> itemCallback = new DiffUtil.ItemCallback<User>() {

        /**
         * 判断item是否相同
         * @param oldItem
         * @param newItem
         * @return
         */
        @Override
        public boolean areItemsTheSame(User oldItem, User newItem) {
            Log.d(TAG, "areItemsTheSame: ");
            return TextUtils.equals(oldItem.id, newItem.id);
        }

        /**
         * 判断item的内容是否相同
         * @param oldItem
         * @param newItem
         * @return
         */
        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(User oldItem, User newItem) {
            Log.d(TAG, "areContentsTheSame: ");
            return oldItem.equals(newItem);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paging);

        recyclerView = findViewById(R.id.recycler_view);

        /**
         * 创建分页配置项
         */
        PagedList.Config config = new PagedList.Config.Builder()
                .setInitialLoadSizeHint(10) //初始加载10页
                .setPageSize(10) //页面大小为10
                .setEnablePlaceholders(false) //不需要占位
                .build();

        /**
         * 创建适配器，传入差分处理接口
         */
        MyAdapter myAdapter = new MyAdapter(itemCallback);

        /**
         * 创建数据源，这里采用LiveData来观察数据
         * 这里需要创建一个DataSourceFactory，传入分配配置项
         *
         * TODO 1 这里的build触发了DataSourceFactory里面的DataSource的loadInitial()
         */
        LiveData<PagedList<User>> pagedListLiveData = new LivePagedListBuilder(new MyDataSourceFactory(), config).build();

        pagedListLiveData.observe(this, new Observer<PagedList<User>>() {
            //TODO 3 被触发
            @Override
            public void onChanged(@Nullable PagedList<User> users) {
                Log.d(TAG, "onChanged: ");
                /**
                 * TODO 4 submit就可以更新数据到UI
                 */
                myAdapter.submitList(users);
            }
        });

        /**
         * 设置适配器
         */
        recyclerView.setAdapter(myAdapter);
    }

    /**
     * 模拟从服务器获取数据
     *
     * @param startPosition
     * @param count
     * @return
     */
    public List<User> loadData(int startPosition, int count) {
        Log.d(TAG, "loadData: " + startPosition + "," + count);
        List<User> users = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            User user = new User();
            user.id = String.valueOf(startPosition + i);
            user.name = "user" + (startPosition + i);
            users.add(user);
        }
        return users;
    }

    /**
     * 提供一个DataSource
     */
    public class MyDataSourceFactory extends DataSource.Factory<Integer, User> {
        @Override
        public DataSource<Integer, User> create() {
            return new MyDataSource();
        }
    }

    /**
     * DataSource提供触发初始化和分页的逻辑
     */
    public class MyDataSource extends PositionalDataSource<User> {
        @Override
        public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<User> callback) {
            Log.d(TAG, "loadInitial: ");
            /**
             * TODO 2 这里加载数据，然后触发PagedListLiveData的onChanged()
             */
            callback.onResult(loadData(0, 10), 0, 10);
        }

        @Override
        public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<User> callback) {
            Log.d(TAG, "loadRange: " + params.startPosition + ", " + params.loadSize);
            /**
             * 这里是分页加载数据: 支持向前向后分页, params.startPosition会自动调整
             */
            callback.onResult(loadData(params.startPosition, 10));
        }
    }

    /**
     * Adapter需要继承PagedListAdapter<数据实体, ViewHolder>
     */
    public class MyAdapter extends PagedListAdapter<User, MyViewHolder> {
        /**
         * 构造函数需要提供一个CallBack
         *
         * @param diffCallback 判断两个item是否相同
         */
        protected MyAdapter(@NonNull DiffUtil.ItemCallback<User> diffCallback) {
            super(diffCallback);
        }

        /**
         * @param config
         */
        public MyAdapter(@NonNull AsyncDifferConfig<User> config) {
            super(config);
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_paging, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            /**
             *  这里使用getItem(position)来获取数据
             */
            User item = getItem(position);
            if (item != null) {
                holder.tvID.setText(item.id);
                holder.tvName.setText(item.name);
            }
        }
    }


    /**
     * ViewHolder不变
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvID;
        TextView tvName;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvID = itemView.findViewById(R.id.tv_id);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }

    /**
     * 数据实体
     */
    public class User {
        public String id;
        public String name;
    }

}
