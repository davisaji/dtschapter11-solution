package id.ac.polinema.dtsfit.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import id.ac.polinema.dtsfit.R;
import id.ac.polinema.dtsfit.adapters.CaloriesAdapter;
import id.ac.polinema.dtsfit.generator.ServiceGenerator;
import id.ac.polinema.dtsfit.models.Calory;
import id.ac.polinema.dtsfit.services.CaloryService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CaloryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CaloryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CaloryFragment extends Fragment implements CaloriesAdapter.OnCaloryClickedListener {

    private RecyclerView caloriesView;
    private FloatingActionButton addButton;

    private CaloryService caloryService;
    private CaloriesAdapter caloriesAdapter;

    private OnFragmentInteractionListener mListener;

    public CaloryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CaloryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CaloryFragment newInstance() {
        CaloryFragment fragment = new CaloryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calory, container, false);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        Context context = getActivity();
        caloriesView = view.findViewById(R.id.rv_calories);
        addButton = view.findViewById(R.id.fab_add);

        // setup recyclerview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        caloriesAdapter = new CaloriesAdapter(context, this);
        caloriesView.setLayoutManager(layoutManager);
        caloriesView.setAdapter(caloriesAdapter);

        caloryService = ServiceGenerator.createService(CaloryService.class);

        if (mListener != null) {
            mListener.onCaloryFragmentCreated(getView(), caloriesAdapter);
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onAddCaloryButtonClicked();
                }
            });
        }
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

    @Override
    public void onCaloryClicked(Calory calory) {
        if (mListener != null) {
            mListener.onCaloryClicked(calory);
        }
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
        void onCaloryFragmentCreated(final View view, final CaloriesAdapter adapter);
        void onAddCaloryButtonClicked();
        void onCaloryClicked(Calory calory);
    }
}
