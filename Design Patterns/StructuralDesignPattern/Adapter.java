package StructuralDesignPattern;

//Target interface
interface MediaPlayer {
    void play(String audioType);
}

class Mp3Player implements MediaPlayer{
    @Override
    public void play(String audioType) {
            System.out.println("Mp3 Player>>>>>>>>>>>> Playing Mp3 file..."+ audioType);

    }
}

//External library with incompatible interface
class VlcCodec{
    public  void playVlc(String fileName){
        System.out.println("Playing Vlc Codec>>>>>>>>>>>> Decoding and playing Vlc file..."+ fileName);
    }
}
class Mp4Codec{
    public void playMp4(String file){
        System.out.println("Playing Mp4 Codec>>>>>>>>>>>> Decoding and playing Mp4 file..."+file);
    }
}

//Adapter class
class VlcPlayerAdapter implements MediaPlayer{
    private VlcCodec vlcCodec;

    public VlcPlayerAdapter(VlcCodec vlcCodec) {
        this.vlcCodec = vlcCodec;
    }
    @Override
    public void play(String audioType) {
       vlcCodec.playVlc(audioType);
    }
}

class Mp4PlayerAdapter implements MediaPlayer{
    private Mp4Codec mp4Codec;

    public Mp4PlayerAdapter(Mp4Codec mp4Codec) {
        this.mp4Codec = mp4Codec;
    }

    @Override
    public void play(String audioType) {
        mp4Codec.playMp4(audioType);
    }
}

//Client code
class AudioPlayer {
    public void playFile(String file) {
        String extension = file.substring(file.lastIndexOf("."));
        MediaPlayer mediaPlayer;
        if (extension.equals(".mp3")) {
            mediaPlayer = new Mp3Player();
        } else if (extension.equals(".vlc")) {
            mediaPlayer = new VlcPlayerAdapter(new VlcCodec());
        } else if (extension.equals(".mp4")) {
            mediaPlayer = new Mp4PlayerAdapter(new Mp4Codec());
        } else {
            System.out.println("Unsupported file format: " + file);
            return;
        }
        mediaPlayer.play(file);
    }
}
    public class Adapter {
        public static void main(String[] args) {
            AudioPlayer audioPlayer = new AudioPlayer();
            audioPlayer.playFile("song.mp3");
            audioPlayer.playFile("movie.vlc");
            audioPlayer.playFile("video.mp4");
            audioPlayer.playFile("document.pdf");
        }
    }

