package com.unicamp.mc322.lab02;

public class User {
    private int MAX_PLAYLIST = 10;
    private String name;
    private String cpf;
    private String birthDate;
    private String genre;
    private boolean sub = false; // subscriber
    private int sizePlaylists = 0;
    private Playlist playlists[] = new Playlist[MAX_PLAYLIST];

    public User(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
    }

    public User(String name, String cpf, String birthDate, String genre, boolean sub) {
        this.name = name;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.genre = genre;
        this.sub = sub;
    }

    private int getMaxPlaylists() {
        return (this.sub ? 10 : 3);
    }

    private int getMaxMusics() {
        return (this.sub ? 100 : 10);
    }

    // add playlist
    public void addPlaylist(Playlist playlist) {
        if (this.getMaxPlaylists() > this.sizePlaylists) {
            this.playlists[this.sizePlaylists++] = playlist;
        } else {
            System.out.print("You can't add playlist!");
        }
    }

    // remove playlist
    public void removePlaylist(String name) {
        int idxPlaylist = this.findPlaylist(name);
        // if playlist exist
        if (idxPlaylist != -1)
            this.playlists[idxPlaylist] = this.playlists[--this.sizePlaylists];
    }

    // return index playlist in array's playlist
    private int findPlaylist(String name) {
        for (int i = 0; i < this.sizePlaylists - 1; i++)
            if (this.playlists[i].getName() == name)
                return (i);
        return (-1);
    }

    // transfer playlist to other user
    public void transferPlaylist(User user, String namePlaylist) {
        int idxPlaylist = this.findPlaylist(namePlaylist);
        user.addPlaylist(this.playlists[idxPlaylist]);
        this.removePlaylist(namePlaylist);
    }
}
