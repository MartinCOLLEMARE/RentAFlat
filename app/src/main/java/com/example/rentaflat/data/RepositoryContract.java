package com.example.rentaflat.data;

import java.util.List;

public interface RepositoryContract {

    interface FetchFlatsDataCallback {
        void onFlatsDataFetched(boolean error);
    }
    interface GetFlatsCallback {
        void setFlats(List<FlatItem> flats);
    }

}
