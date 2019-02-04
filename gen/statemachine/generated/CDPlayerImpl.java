// Automatically generated code, do not edit
package statemachine.generated;
import java.util.Map;
import statemachine.year4.codegen.GeneratedMachine;
public class CDPlayerImpl extends GeneratedMachine {
  @Override protected void internalProcessEvent(int event) {
    switch(state) {
    case 0: // STOPPED
      switch(event) {
      case 0: // PLAY
        if(track==0) {
          track=1;
          state = 1; // PLAYING
        }
      else
        {
          state = 1; // PLAYING
        }
      break;
      case 1: // BACK
        if(track>1) {
          state = 0; // STOPPED
        }
      break;
      case 2: // FORWARD
        {
          track+=1;
          state = 0; // STOPPED
        }
      break;
      default: ; // ignore
      }
    break;
    case 1: // PLAYING
      switch(event) {
      case 3: // PAUSE
        {
          state = 2; // PAUSED
        }
      break;
      case 4: // STOP
        {
          track=0;
          state = 0; // STOPPED
        }
      break;
      case 5: // TRACK_END
        {
          track+=1;
          state = 1; // PLAYING
        }
      break;
      default: ; // ignore
      }
    break;
    case 2: // PAUSED
      switch(event) {
      case 0: // PLAY
        {
          state = 1; // PLAYING
        }
      break;
      case 4: // STOP
        {
          state = 0; // STOPPED
        }
      break;
      case 1: // BACK
        if(track>1) {
          track+=-1;
          state = 2; // PAUSED
        }
      break;
      case 2: // FORWARD
        {
          track+=1;
          state = 2; // PAUSED
        }
      break;
      default: ; // ignore
      }
    break;
    default: throw new Error("Internal error: unsupported state code");
    }
  }
  private int track;
  /** Get the value of the extended state track
    * @return value of track
  */
  public int get_track() { return track; }
  @Override public String getRuntimeStateFor(String what) {
    return "track".equals(what)?Integer.toString(track):super.getRuntimeStateFor(what);
  }
  @Override protected void internalInitialize(Map<String, Integer> event_code2int, Map<Integer, String> state_int2code) {
    state_int2code.put(2,"PAUSED");
    state_int2code.put(0,"STOPPED");
    state_int2code.put(1,"PLAYING");
    event_code2int.put("PAUSE",3);
    event_code2int.put("PLAY",0);
    event_code2int.put("STOP",4);
    event_code2int.put("BACK",1);
    event_code2int.put("FORWARD",2);
    event_code2int.put("TRACK_END",5);
  }
}
