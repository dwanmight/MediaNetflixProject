
    private void getFilmWithDirector(String director) {
        Call<List<VideoInfoResDirector>> call=mDataManager.getVideoWithDirector(director);
//        Call<List<VideoInfoResDirector>> call = mDataManager.getVideoWithDirectorQuen();
        list = new ArrayList<VideoInfoResDirector>();
        call.enqueue(new Callback<List<VideoInfoResDirector>>() {

            @Override
            public void onResponse(Call<List<VideoInfoResDirector>> call, Response<List<VideoInfoResDirector>> response) {
                if (response.code() == 200) {
                    mListView.setVisibility(View.VISIBLE);
                    mSearchView.setVisibility(View.GONE);
                    System.out.println("200 CODE");
                    for (int i = 0; i < response.body().size(); i++) {
                        list.add(response.body().get(i));
                        FilmAbout fa=new FilmAbout();
                        fa.setTitle(list.get(i).getShowTitle());
                        fa.setDirector(list.get(i).getDirector());
                        fa.setPoster(response.body().get(i).getPoster());
                        System.out.println("image ="+response.body().get(i).getPoster());
                        listFilm.add(fa);
                        Log.i("TAG","film "+listFilm.get(i).getTitle());
                        Log.i("TAG"," list"+list.get(i).getShowTitle());


                    }


                    for (VideoInfoResDirector s : list) {
                        System.out.println(s.getShowTitle());
                    }

                }
            }

            @Override
            public void onFailure(Call<List<VideoInfoResDirector>> call, Throwable t) {

            }
        });
    }


