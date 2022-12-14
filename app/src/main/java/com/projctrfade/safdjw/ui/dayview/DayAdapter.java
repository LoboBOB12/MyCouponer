package com.projctrfade.safdjw.ui.dayview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.projctrfade.safdjw.R;

import java.util.List;

class DayAdapter extends RecyclerView.Adapter<DayAdapter.BaseViewHolder> {

    private final List<DayListItemView> data;
    private final DayAdapterEventHandler callback;

    public DayAdapter(List<DayListItemView> data, DayAdapterEventHandler callback) {
        super();
        this.data = data;
        this.callback = callback;
    }

    public interface DayAdapterEventHandler {

        void onItemClick(int hourInteger);
    }

    @Override
    public int getItemViewType(int position) {
        switch (data.get(position).getType()) {
            case HALF_HALF:
                return 1;
            case QUARTER_QUARTER_HALF:
                return 2;
            case QUARTER_HALF_QUARTER:
                return 3;
            case HALF_QUARTER_QUARTER:
                return 4;
            case QUARTER_QUARTER_QUARTER_QUARTER:
                return 5;
            case QUARTER_THREE_QUARTER:
                return 6;
            case THREE_QUARTER_QUARTER:
                return 7;
            default:
                //default one hour
                return 0;
        }
    }

    @Override
    public int getItemCount() {
        return 24;
    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        BaseViewHolder vh = null;
        View view = null;

        switch (viewType) {
            case 0:
                view = inflater.inflate(R.layout.list_item_day_view_full_hour,
                        parent,
                        false);

                vh = new SingleTaskViewHolder(view);
                break;
            case 1:
                view = inflater.inflate(R.layout.list_item_day_view_half_and_half,
                        parent,
                        false);

                vh = new TwoTaskViewHolder(view);
                break;
            case 2:
                view = inflater.inflate(R.layout.list_item_day_view_quarter_quarter_half,
                        parent,
                        false);

                vh = new ThreeTaskViewHolder(view);
                break;
            case 3:
                view = inflater.inflate(R.layout.list_item_day_view_quarter_half_quarter,
                        parent,
                        false);

                vh = new ThreeTaskViewHolder(view);

                break;
            case 4:
                view = inflater.inflate(R.layout.list_item_day_view_half_quarter_quarter,
                        parent,
                        false);

                vh = new ThreeTaskViewHolder(view);

                break;

            case 5:
                view = inflater.inflate(R.layout.list_item_day_view_quarters,
                        parent,
                        false);
                vh = new FourTaskViewHolder(view);

                break;

            case 6:
                view = inflater.inflate(R.layout.list_item_day_view_quarter_three_quarter,
                        parent,
                        false);
                vh = new TwoTaskViewHolder(view);

                break;
            case 7:
                view = inflater.inflate(R.layout.list_item_day_view_three_quarter_quarter,
                        parent,
                        false);
                vh = new TwoTaskViewHolder(view);

                break;
        }

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        DayListItemView item = data.get(position);
        holder.hourBlockText.setText(item.getHourBlockText());
        holder.root.setOnClickListener(
                view -> {
                    callback.onItemClick(position);
                }
        );


        switch (item.getType()) {
            case FULL_HOUR:
                SingleTaskViewHolder svh = (SingleTaskViewHolder) holder;
                svh.firstTaskText.setText(item.getTaskNames()[0]);
                svh.firstTaskIcon.setImageResource(item.getIconResId()[0]);
                svh.firstTaskBackground.setImageResource(item.getBackgroundResId()[0]);

                break;
            case HALF_HALF:
                bindTwoTaskViewHolder( (TwoTaskViewHolder) holder,
                        item,
                        0,
                        2);
                break;
            case QUARTER_QUARTER_HALF:
                bindThreeTaskViewHolder((ThreeTaskViewHolder) holder,
                        item,
                        0,
                        1,
                        2
                );
                break;
            case QUARTER_HALF_QUARTER:
                bindThreeTaskViewHolder((ThreeTaskViewHolder) holder,
                        item,
                        0,
                        1,
                        3
                );
                break;
            case HALF_QUARTER_QUARTER:
                bindThreeTaskViewHolder((ThreeTaskViewHolder) holder,
                        item,
                        0,
                        2,
                        3
                );
                break;
            case QUARTER_QUARTER_QUARTER_QUARTER:
                FourTaskViewHolder fvh = (FourTaskViewHolder) holder;

                fvh.firstTaskText.setText(item.getTaskNames()[0]);
                fvh.firstTaskIcon.setImageResource(item.getIconResId()[0]);
                fvh.firstTaskBackground.setImageResource(item.getBackgroundResId()[0]);

                fvh.secondTaskText.setText(item.getTaskNames()[1]);
                fvh.secondTaskIcon.setImageResource(item.getIconResId()[1]);
                fvh.secondTaskBackground.setImageResource(item.getBackgroundResId()[1]);

                fvh.thirdTaskText.setText(item.getTaskNames()[2]);
                fvh.thirdTaskIcon.setImageResource(item.getIconResId()[2]);
                fvh.thirdTaskBackground.setImageResource(item.getBackgroundResId()[2]);

                fvh.fourthTaskText.setText(item.getTaskNames()[3]);
                fvh.fourthTaskIcon.setImageResource(item.getIconResId()[3]);
                fvh.fourthTaskBackground.setImageResource(item.getBackgroundResId()[3]);
                break;

            case QUARTER_THREE_QUARTER:
                bindTwoTaskViewHolder(
                        (TwoTaskViewHolder) holder,
                        item,
                        0,
                        1
                );
                TwoTaskViewHolder twvhTwo = (TwoTaskViewHolder) holder;
                twvhTwo.firstTaskText.setText(item.getTaskNames()[0]);
                twvhTwo.firstTaskIcon.setImageResource(item.getIconResId()[0]);
                twvhTwo.firstTaskBackground.setImageResource(item.getBackgroundResId()[0]);

                twvhTwo.secondTaskText.setText(item.getTaskNames()[1]);
                twvhTwo.secondTaskIcon.setImageResource(item.getIconResId()[1]);
                twvhTwo.secondTaskBackground.setImageResource(item.getBackgroundResId()[1]);
                break;

            case THREE_QUARTER_QUARTER:
                bindTwoTaskViewHolder(
                        (TwoTaskViewHolder) holder,
                        item,
                        0,
                        3
                );

                break;
        }
    }


    private void bindThreeTaskViewHolder(
            ThreeTaskViewHolder fvh,
            DayListItemView item,
            int firstIndex,
            int secondIndex,
            int thirdIndex) {
        fvh.firstTaskText.setText(item.getTaskNames()[firstIndex]);
        fvh.firstTaskIcon.setImageResource(item.getIconResId()[firstIndex]);
        fvh.firstTaskBackground.setImageResource(item.getBackgroundResId()[firstIndex]);

        fvh.secondTaskText.setText(item.getTaskNames()[secondIndex]);
        fvh.secondTaskIcon.setImageResource(item.getIconResId()[secondIndex]);
        fvh.secondTaskBackground.setImageResource(item.getBackgroundResId()[secondIndex]);

        fvh.thirdTaskText.setText(item.getTaskNames()[thirdIndex]);
        fvh.thirdTaskIcon.setImageResource(item.getIconResId()[thirdIndex]);
        fvh.thirdTaskBackground.setImageResource(item.getBackgroundResId()[thirdIndex]);
    }

    private void bindTwoTaskViewHolder(
            TwoTaskViewHolder tvh,
            DayListItemView item,
            int firstIndex,
            int secondIndex) {
        tvh.firstTaskText.setText(item.getTaskNames()[firstIndex]);
        tvh.firstTaskIcon.setImageResource(item.getIconResId()[firstIndex]);
        tvh.firstTaskBackground.setImageResource(item.getBackgroundResId()[firstIndex]);

        tvh.secondTaskText.setText(item.getTaskNames()[secondIndex]);
        tvh.secondTaskIcon.setImageResource(item.getIconResId()[secondIndex]);
        tvh.secondTaskBackground.setImageResource(item.getBackgroundResId()[secondIndex]);
    }

    class BaseViewHolder extends RecyclerView.ViewHolder {
        View root;
        TextView hourBlockText;

        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.list_item_root);
            hourBlockText = itemView.findViewById(R.id.lbl_hour_block);
        }
    }


    class SingleTaskViewHolder extends BaseViewHolder {
        public TextView firstTaskText;
        ImageView firstTaskIcon;
        ImageView firstTaskBackground;


        public SingleTaskViewHolder(@NonNull View itemView) {
            super(itemView);

            firstTaskText = itemView.findViewById(R.id.lbl_first_task_text);
            firstTaskIcon = itemView.findViewById(R.id.imv_first_task_icon);
            firstTaskBackground = itemView.findViewById(R.id.imv_first_task_background);
        }
    }


    class TwoTaskViewHolder extends BaseViewHolder {

        TextView firstTaskText;
        ImageView firstTaskIcon;
        ImageView firstTaskBackground;

        TextView secondTaskText;
        ImageView secondTaskIcon;
        ImageView secondTaskBackground;

        public TwoTaskViewHolder(@NonNull View itemView) {
            super(itemView);

            firstTaskText = itemView.findViewById(R.id.lbl_first_task_text);
            firstTaskIcon = itemView.findViewById(R.id.imv_first_task_icon);
            firstTaskBackground = itemView.findViewById(R.id.imv_first_task_background);

            secondTaskText = itemView.findViewById(R.id.lbl_second_task_text);
            secondTaskIcon = itemView.findViewById(R.id.imv_second_task_icon);
            secondTaskBackground = itemView.findViewById(R.id.imv_second_task_background);
        }
    }


    class ThreeTaskViewHolder extends BaseViewHolder {
        public TextView firstTaskText;
        ImageView firstTaskIcon;
        ImageView firstTaskBackground;

        TextView secondTaskText;
        ImageView secondTaskIcon;
        ImageView secondTaskBackground;

        TextView thirdTaskText;
        ImageView thirdTaskIcon;
        ImageView thirdTaskBackground;

        public ThreeTaskViewHolder(@NonNull View itemView) {
            super(itemView);

            firstTaskText = itemView.findViewById(R.id.lbl_first_task_text);
            firstTaskIcon = itemView.findViewById(R.id.imv_first_task_icon);
            firstTaskBackground = itemView.findViewById(R.id.imv_first_task_background);

            secondTaskText = itemView.findViewById(R.id.lbl_second_task_text);
            secondTaskIcon = itemView.findViewById(R.id.imv_second_task_icon);
            secondTaskBackground = itemView.findViewById(R.id.imv_second_task_background);

            thirdTaskText = itemView.findViewById(R.id.lbl_third_task_text);
            thirdTaskIcon = itemView.findViewById(R.id.imv_third_task_icon);
            thirdTaskBackground = itemView.findViewById(R.id.imv_third_task_background);

        }
    }


    class FourTaskViewHolder extends BaseViewHolder {
        TextView firstTaskText;
        ImageView firstTaskIcon;
        ImageView firstTaskBackground;

        TextView secondTaskText;
        ImageView secondTaskIcon;
        ImageView secondTaskBackground;

        TextView thirdTaskText;
        ImageView thirdTaskIcon;
        ImageView thirdTaskBackground;

        TextView fourthTaskText;
        ImageView fourthTaskIcon;
        ImageView fourthTaskBackground;

        public FourTaskViewHolder(@NonNull View itemView) {
            super(itemView);

            firstTaskText = itemView.findViewById(R.id.lbl_first_task_text);
            firstTaskIcon = itemView.findViewById(R.id.imv_first_task_icon);
            firstTaskBackground = itemView.findViewById(R.id.imv_first_task_background);

            secondTaskText = itemView.findViewById(R.id.lbl_second_task_text);
            secondTaskIcon = itemView.findViewById(R.id.imv_second_task_icon);
            secondTaskBackground = itemView.findViewById(R.id.imv_second_task_background);

            thirdTaskText = itemView.findViewById(R.id.lbl_third_task_text);
            thirdTaskIcon = itemView.findViewById(R.id.imv_third_task_icon);
            thirdTaskBackground = itemView.findViewById(R.id.imv_third_task_background);

            fourthTaskText = itemView.findViewById(R.id.lbl_fourth_task_text);
            fourthTaskIcon = itemView.findViewById(R.id.imv_fourth_task_icon);
            fourthTaskBackground = itemView.findViewById(R.id.imv_fourth_task_background);
        }
    }

}
