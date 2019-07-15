package id.ac.polinema.dtsfit.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.textfield.TextInputEditText;

import id.ac.polinema.dtsfit.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    private TextInputEditText nameText;
    private RadioGroup genderGroup;
    private AppCompatRadioButton maleRadio;
    private AppCompatRadioButton femaleRadio;
    private TextInputEditText heightText;
    private TextInputEditText weightText;
    private TextInputEditText dobText;
    private AppCompatSpinner activitiesSpinner;
    private AppCompatButton saveButton;

    private OnFragmentInteractionListener mListener;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        initComponents(view);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_profile, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void initComponents(View view) {
        nameText = view.findViewById(R.id.input_name);
        genderGroup = view.findViewById(R.id.group_gender);
//        maleRadio = view.findViewById(R.id.radio_male);
//        femaleRadio = view.findViewById(R.id.radio_female);
        heightText = view.findViewById(R.id.input_height);
        weightText = view.findViewById(R.id.input_weight);
        dobText = view.findViewById(R.id.input_dob);
        activitiesSpinner = view.findViewById(R.id.spinner_activities);
        saveButton = view.findViewById(R.id.button_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioButton selectedRadioButton = view.findViewById(genderGroup.getCheckedRadioButtonId());
                String gender = selectedRadioButton.getText().toString();
                float height = Float.parseFloat(heightText.getText().toString());
                float weight = Float.parseFloat(weightText.getText().toString());
                int age = 31;
                int activity = activitiesSpinner.getSelectedItemPosition();

                float bmr = calculateBmr(gender, height, weight, age, activity);
            }
        });
    }

    private float calculateBmr(String gender, float height, float weight, int age, int activity) {
        float result = (gender.equals("Male"))
                ? 66 + (13.7f + weight) + (5f * height) - (6.8f * age)
                : 655 + (9.6f * weight) + (1.8f * height) - (4.7f * age);
        if (activity == 0) {
            result *= 1.2f;
        } else if (activity == 1) {
            result *= 1.375f;
        } else if (activity == 2) {
            result *= 1.55f;
        } else if (activity == 3) {
            result *= 1.755f;
        } else {
            result *= 1.9f;
        }

        return result;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
    }
}
