// Autogenerated from Pigeon (v3.1.0), do not edit directly.
// See also: https://pub.dev/packages/pigeon

package com.bqt.test.flutter.channel.pigeon;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.flutter.plugin.common.BasicMessageChannel;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MessageCodec;
import io.flutter.plugin.common.StandardMessageCodec;

/** Generated class from Pigeon. */
@SuppressWarnings({"unused", "unchecked", "CodeBlock2Expr", "RedundantSuppression"})
public class Pigeon {

  public enum StateEnum {
    success(0),
    error(1),
    unknown(2);

    private int index;
    private StateEnum(final int index) {
      this.index = index;
    }
  }

  /** Generated class from Pigeon that represents data sent in messages. */
  public static class Book {
    private @Nullable Long id;
    public @Nullable Long getId() { return id; }
    public void setId(@Nullable Long setterArg) {
      this.id = setterArg;
    }

    private @Nullable String title;
    public @Nullable String getTitle() { return title; }
    public void setTitle(@Nullable String setterArg) {
      this.title = setterArg;
    }

    private @Nullable Author author;
    public @Nullable Author getAuthor() { return author; }
    public void setAuthor(@Nullable Author setterArg) {
      this.author = setterArg;
    }

    public static final class Builder {
      private @Nullable Long id;
      public @NonNull Builder setId(@Nullable Long setterArg) {
        this.id = setterArg;
        return this;
      }
      private @Nullable String title;
      public @NonNull Builder setTitle(@Nullable String setterArg) {
        this.title = setterArg;
        return this;
      }
      private @Nullable Author author;
      public @NonNull Builder setAuthor(@Nullable Author setterArg) {
        this.author = setterArg;
        return this;
      }
      public @NonNull Book build() {
        Book pigeonReturn = new Book();
        pigeonReturn.setId(id);
        pigeonReturn.setTitle(title);
        pigeonReturn.setAuthor(author);
        return pigeonReturn;
      }
    }
    @NonNull Map<String, Object> toMap() {
      Map<String, Object> toMapResult = new HashMap<>();
      toMapResult.put("id", id);
      toMapResult.put("title", title);
      toMapResult.put("author", (author == null) ? null : author.toMap());
      return toMapResult;
    }
    static @NonNull Book fromMap(@NonNull Map<String, Object> map) {
      Book pigeonResult = new Book();
      Object id = map.get("id");
      pigeonResult.setId((id == null) ? null : ((id instanceof Integer) ? (Integer)id : (Long)id));
      Object title = map.get("title");
      pigeonResult.setTitle((String)title);
      Object author = map.get("author");
      pigeonResult.setAuthor((author == null) ? null : Author.fromMap((Map)author));
      return pigeonResult;
    }
  }

  /** Generated class from Pigeon that represents data sent in messages. */
  public static class Author {
    private @Nullable String name;
    public @Nullable String getName() { return name; }
    public void setName(@Nullable String setterArg) {
      this.name = setterArg;
    }

    private @Nullable Boolean male;
    public @Nullable Boolean getMale() { return male; }
    public void setMale(@Nullable Boolean setterArg) {
      this.male = setterArg;
    }

    private @Nullable StateEnum state;
    public @Nullable StateEnum getState() { return state; }
    public void setState(@Nullable StateEnum setterArg) {
      this.state = setterArg;
    }

    public static final class Builder {
      private @Nullable String name;
      public @NonNull Builder setName(@Nullable String setterArg) {
        this.name = setterArg;
        return this;
      }
      private @Nullable Boolean male;
      public @NonNull Builder setMale(@Nullable Boolean setterArg) {
        this.male = setterArg;
        return this;
      }
      private @Nullable StateEnum state;
      public @NonNull Builder setState(@Nullable StateEnum setterArg) {
        this.state = setterArg;
        return this;
      }
      public @NonNull Author build() {
        Author pigeonReturn = new Author();
        pigeonReturn.setName(name);
        pigeonReturn.setMale(male);
        pigeonReturn.setState(state);
        return pigeonReturn;
      }
    }
    @NonNull Map<String, Object> toMap() {
      Map<String, Object> toMapResult = new HashMap<>();
      toMapResult.put("name", name);
      toMapResult.put("male", male);
      toMapResult.put("state", state == null ? null : state.index);
      return toMapResult;
    }
    static @NonNull Author fromMap(@NonNull Map<String, Object> map) {
      Author pigeonResult = new Author();
      Object name = map.get("name");
      pigeonResult.setName((String)name);
      Object male = map.get("male");
      pigeonResult.setMale((Boolean)male);
      Object state = map.get("state");
      pigeonResult.setState(state == null ? null : StateEnum.values()[(int)state]);
      return pigeonResult;
    }
  }

  public interface Result<T> {
    void success(T result);
    void error(Throwable error);
  }
  private static class TestBookApiCodec extends StandardMessageCodec {
    public static final TestBookApiCodec INSTANCE = new TestBookApiCodec();
    private TestBookApiCodec() {}
    @Override
    protected Object readValueOfType(byte type, ByteBuffer buffer) {
      switch (type) {
        case (byte)128:         
          return Author.fromMap((Map<String, Object>) readValue(buffer));
        
        case (byte)129:         
          return Book.fromMap((Map<String, Object>) readValue(buffer));
        
        case (byte)130:         
          return Book.fromMap((Map<String, Object>) readValue(buffer));
        
        default:        
          return super.readValueOfType(type, buffer);
        
      }
    }
    @Override
    protected void writeValue(ByteArrayOutputStream stream, Object value)     {
      if (value instanceof Author) {
        stream.write(128);
        writeValue(stream, ((Author) value).toMap());
      } else 
      if (value instanceof Book) {
        stream.write(129);
        writeValue(stream, ((Book) value).toMap());
      } else 
      if (value instanceof Book) {
        stream.write(130);
        writeValue(stream, ((Book) value).toMap());
      } else 
{
        super.writeValue(stream, value);
      }
    }
  }

  /** Generated interface from Pigeon that represents a handler of messages from Flutter.*/
  public interface TestBookApi {
    @Nullable Book search(@Nullable String keyword);
    @NonNull List<Book> searchList(@NonNull String keyword);
    @NonNull List<Book> searchList2(@NonNull List<String> keys);
    void testNoArguments();

    /** The codec used by TestBookApi. */
    static MessageCodec<Object> getCodec() {
      return TestBookApiCodec.INSTANCE;
    }

    /** Sets up an instance of `TestBookApi` to handle messages through the `binaryMessenger`. */
    static void setup(BinaryMessenger binaryMessenger, TestBookApi api) {
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.TestBookApi.search", getCodec());
        if (api != null) {
          channel.setMessageHandler((message, reply) -> {
            Map<String, Object> wrapped = new HashMap<>();
            try {
              ArrayList<Object> args = (ArrayList<Object>)message;
              String keywordArg = (String)args.get(0);
              Book output = api.search(keywordArg);
              wrapped.put("result", output);
            }
            catch (Error | RuntimeException exception) {
              wrapped.put("error", wrapError(exception));
            }
            reply.reply(wrapped);
          });
        } else {
          channel.setMessageHandler(null);
        }
      }
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.TestBookApi.searchList", getCodec());
        if (api != null) {
          channel.setMessageHandler((message, reply) -> {
            Map<String, Object> wrapped = new HashMap<>();
            try {
              ArrayList<Object> args = (ArrayList<Object>)message;
              String keywordArg = (String)args.get(0);
              if (keywordArg == null) {
                throw new NullPointerException("keywordArg unexpectedly null.");
              }
              List<Book> output = api.searchList(keywordArg);
              wrapped.put("result", output);
            }
            catch (Error | RuntimeException exception) {
              wrapped.put("error", wrapError(exception));
            }
            reply.reply(wrapped);
          });
        } else {
          channel.setMessageHandler(null);
        }
      }
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.TestBookApi.searchList2", getCodec());
        if (api != null) {
          channel.setMessageHandler((message, reply) -> {
            Map<String, Object> wrapped = new HashMap<>();
            try {
              ArrayList<Object> args = (ArrayList<Object>)message;
              List<String> keysArg = (List<String>)args.get(0);
              if (keysArg == null) {
                throw new NullPointerException("keysArg unexpectedly null.");
              }
              List<Book> output = api.searchList2(keysArg);
              wrapped.put("result", output);
            }
            catch (Error | RuntimeException exception) {
              wrapped.put("error", wrapError(exception));
            }
            reply.reply(wrapped);
          });
        } else {
          channel.setMessageHandler(null);
        }
      }
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.TestBookApi.testNoArguments", getCodec());
        if (api != null) {
          channel.setMessageHandler((message, reply) -> {
            Map<String, Object> wrapped = new HashMap<>();
            try {
              api.testNoArguments();
              wrapped.put("result", null);
            }
            catch (Error | RuntimeException exception) {
              wrapped.put("error", wrapError(exception));
            }
            reply.reply(wrapped);
          });
        } else {
          channel.setMessageHandler(null);
        }
      }
    }
  }
  private static class TestAsyApiCodec extends StandardMessageCodec {
    public static final TestAsyApiCodec INSTANCE = new TestAsyApiCodec();
    private TestAsyApiCodec() {}
  }

  /** Generated interface from Pigeon that represents a handler of messages from Flutter.*/
  public interface TestAsyApi {
    void calculate(@NonNull Long key, Result<String> result);

    /** The codec used by TestAsyApi. */
    static MessageCodec<Object> getCodec() {
      return TestAsyApiCodec.INSTANCE;
    }

    /** Sets up an instance of `TestAsyApi` to handle messages through the `binaryMessenger`. */
    static void setup(BinaryMessenger binaryMessenger, TestAsyApi api) {
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.TestAsyApi.calculate", getCodec());
        if (api != null) {
          channel.setMessageHandler((message, reply) -> {
            Map<String, Object> wrapped = new HashMap<>();
            try {
              ArrayList<Object> args = (ArrayList<Object>)message;
              Number keyArg = (Number)args.get(0);
              if (keyArg == null) {
                throw new NullPointerException("keyArg unexpectedly null.");
              }
              Result<String> resultCallback = new Result<String>() {
                public void success(String result) {
                  wrapped.put("result", result);
                  reply.reply(wrapped);
                }
                public void error(Throwable error) {
                  wrapped.put("error", wrapError(error));
                  reply.reply(wrapped);
                }
              };

              api.calculate((keyArg == null) ? null : keyArg.longValue(), resultCallback);
            }
            catch (Error | RuntimeException exception) {
              wrapped.put("error", wrapError(exception));
              reply.reply(wrapped);
            }
          });
        } else {
          channel.setMessageHandler(null);
        }
      }
    }
  }
  private static class TestTaskQueueApiCodec extends StandardMessageCodec {
    public static final TestTaskQueueApiCodec INSTANCE = new TestTaskQueueApiCodec();
    private TestTaskQueueApiCodec() {}
  }

  /** Generated interface from Pigeon that represents a handler of messages from Flutter.*/
  public interface TestTaskQueueApi {
    @NonNull Long add(@NonNull Long x, @NonNull Long y);

    /** The codec used by TestTaskQueueApi. */
    static MessageCodec<Object> getCodec() {
      return TestTaskQueueApiCodec.INSTANCE;
    }

    /** Sets up an instance of `TestTaskQueueApi` to handle messages through the `binaryMessenger`. */
    static void setup(BinaryMessenger binaryMessenger, TestTaskQueueApi api) {
      {
        BinaryMessenger.TaskQueue taskQueue = binaryMessenger.makeBackgroundTaskQueue();
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.TestTaskQueueApi.add", getCodec(), taskQueue);
        if (api != null) {
          channel.setMessageHandler((message, reply) -> {
            Map<String, Object> wrapped = new HashMap<>();
            try {
              ArrayList<Object> args = (ArrayList<Object>)message;
              Number xArg = (Number)args.get(0);
              if (xArg == null) {
                throw new NullPointerException("xArg unexpectedly null.");
              }
              Number yArg = (Number)args.get(1);
              if (yArg == null) {
                throw new NullPointerException("yArg unexpectedly null.");
              }
              Long output = api.add((xArg == null) ? null : xArg.longValue(), (yArg == null) ? null : yArg.longValue());
              wrapped.put("result", output);
            }
            catch (Error | RuntimeException exception) {
              wrapped.put("error", wrapError(exception));
            }
            reply.reply(wrapped);
          });
        } else {
          channel.setMessageHandler(null);
        }
      }
    }
  }
  private static class TestFlutterApiCodec extends StandardMessageCodec {
    public static final TestFlutterApiCodec INSTANCE = new TestFlutterApiCodec();
    private TestFlutterApiCodec() {}
  }

  /** Generated class from Pigeon that represents Flutter messages that can be called from Java.*/
  public static class TestFlutterApi {
    private final BinaryMessenger binaryMessenger;
    public TestFlutterApi(BinaryMessenger argBinaryMessenger){
      this.binaryMessenger = argBinaryMessenger;
    }
    public interface Reply<T> {
      void reply(T reply);
    }
    static MessageCodec<Object> getCodec() {
      return TestFlutterApiCodec.INSTANCE;
    }

    public void getYourName(@NonNull Long keyArg, Reply<String> callback) {
      BasicMessageChannel<Object> channel =
          new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.TestFlutterApi.getYourName", getCodec());
      channel.send(new ArrayList<Object>(Arrays.asList(keyArg)), channelReply -> {
        @SuppressWarnings("ConstantConditions")
        String output = (String)channelReply;
        callback.reply(output);
      });
    }
  }
  private static Map<String, Object> wrapError(Throwable exception) {
    Map<String, Object> errorMap = new HashMap<>();
    errorMap.put("message", exception.toString());
    errorMap.put("code", exception.getClass().getSimpleName());
    errorMap.put("details", "Cause: " + exception.getCause() + ", Stacktrace: " + Log.getStackTraceString(exception));
    return errorMap;
  }
}
