package tk.julianjurec.linuxsession14.Speakers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tk.julianjurec.linuxsession14.Model.Lecture;
import tk.julianjurec.linuxsession14.Model.Speaker;
import tk.julianjurec.linuxsession14.R;

import static android.view.View.GONE;

/**
 * Created by sp0rk on 01.05.17.
 */

public class SpeakersDialogFragment extends DialogFragment {
    private Speaker speaker;
    private Lecture lecture;
    @BindView(R.id.dialog_speaker_img)
    ImageView img;
    @BindView(R.id.dialog_speaker_name)
    TextView name;
    @BindView(R.id.dialog_speaker_description)
    TextView description;
    @BindView(R.id.dialog_speaker_lecture_name)
    TextView lectureName;
    @BindView(R.id.dialog_speaker_lecture_time)
    TextView lectureTime;
    @BindView(R.id.dialog_speaker_lecture_card)
    CardView lectureCard;
    private SpeakersPresenter presenter;

    public static SpeakersDialogFragment newInstance(Speaker speaker, Lecture lecture) {
        Bundle args = new Bundle();
        args.putSerializable("speaker", speaker);
        args.putSerializable("lecture", lecture);
        SpeakersDialogFragment fragment = new SpeakersDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public SpeakersDialogFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        speaker = (Speaker) getArguments().getSerializable("speaker");
        lecture = (Lecture) getArguments().getSerializable("lecture");
        presenter = ((SpeakersFragment)getParentFragment()).getPresenter();
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.SpeakerDialog);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_speaker, container, false);
        ButterKnife.bind(this, v);
        onBindView();
        return v;
    }

    private void onBindView() {
        if (speaker.getImgUrl() != null && !speaker.getImgUrl().isEmpty())
            Picasso.with(getContext())
                    .load(speaker.getImgUrl())
                    .placeholder(R.drawable.unknown)
                    .into(img);
        name.setText(speaker.getName());
        description.setText(speaker.getDescription());

        if (lecture != null) {
            lectureName.setText(lecture.getTitle());
            lectureTime.setText((lecture.getDay() == 1 ? "Sobota " : "Niedziela ") + lecture.getStartTime());
        } else
            lectureCard.setVisibility(GONE);

    }

    @OnClick(R.id.dialog_speaker_outside)
    public void dismissOnClickOutside() {
        dismiss();
    }

    @OnClick(R.id.dialog_speaker_lecture_card)
    public void launchLecture(){
        if (presenter != null) {
            presenter.showLecture(lecture);
        }
    }
}
