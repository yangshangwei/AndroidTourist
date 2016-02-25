package com.turing.base.activity.dataStore.fileStore;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 用于适配不同型号手机，反射获取SD卡路径和状态
 *
 * @author johdan
 */
public class DevMountInfo {

    private final String TAG = DevMountInfo.class.getSimpleName();

    private static final int ERROR = -1;

    // class name
    private final static String CLASS_NAME = "android.os.storage.StorageVolume";

    //remained spare memory size
    private static final int REMAINED_SPARE_IN_MB = 100;

    // method name
    private final static String METHOD_GET_VOLUME_LIST = "getVolumeList";
    private final static String METHOD_GET_VOLUME_STATE = "getVolumeState";
    private final static String METHOD_IS_REMOVABLE = "isRemovable";
    private final static String METHOD_GET_PATH = "getPath";

    private final static String MOUNTED = "mounted";

    private static DevMountInfo INSTANCE;

    private String mSDCardPath = null;

    // internal file path
    private ConcurrentLinkedQueue<String> mInternalPathList = new ConcurrentLinkedQueue<String>();
    // external file path
    private ConcurrentLinkedQueue<String> mExternalPathList = new ConcurrentLinkedQueue<String>();

    private ExecutorService mExecutor = null;

    private DevMountInfo() {
        mExecutor = Executors.newSingleThreadExecutor();
    }

    public static DevMountInfo getInstance() {
        synchronized (DevMountInfo.class) {
            if (null == INSTANCE) {
                INSTANCE = new DevMountInfo();
            }
            return INSTANCE;
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        synchronized (DevMountInfo.class) {
            mInternalPathList.clear();
            mExternalPathList.clear();
            mExecutor.shutdown();
            INSTANCE = null;
        }
    }

    public void init(final Context context) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                executeInit(context);
            }
        });
    }

    public boolean isSDCardFull() {
        return REMAINED_SPARE_IN_MB > (getSDCardAvailSpace() * 1024);
    }

    public boolean isSDCardAvaiable() {

        return !mExternalPathList.isEmpty() || !mInternalPathList.isEmpty();
    }

    public String getSDCardPath() {
        return mSDCardPath;
    }

    public long getSDCardTotalSpace() {
        long totalSpace = 0;
        if (!TextUtils.isEmpty(mSDCardPath)) {
            StatFs sf = new StatFs(mSDCardPath);
            long blockSize = sf.getBlockSize();
            long total = sf.getBlockCount();
            totalSpace = total * blockSize / 1024;
        }
        return totalSpace;
    }

    public long getSDCardAvailSpace() {
        long availSpace = 0;
        if (!TextUtils.isEmpty(mSDCardPath)) {
            StatFs sf = new StatFs(mSDCardPath);
            long blockSize = sf.getBlockSize();
            long availCount = sf.getAvailableBlocks();
            availSpace = availCount * blockSize / 1024;
        }
        return availSpace;
    }

    public String getInternalSDCardPath() {

        return mInternalPathList.peek();
    }

    public String getExternalSDCardPath() {

        return mExternalPathList.peek();
    }

    private void executeInit(Context context) {
        StorageManager mStorageManager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
        if (mStorageManager != null) {
            Class<?> mStorageVolume = null;
            Method mGetVolumeListMethod = null;
            Method mGetVolumeStateMethod = null;
            Method mGetPathMethod = null;
            Method mIsRemovableMethod = null;
            Object[] mStorageVolumeList = null;
            try {
                mStorageVolume = Class.forName(CLASS_NAME);
                mGetVolumeListMethod = mStorageManager.getClass().getMethod(METHOD_GET_VOLUME_LIST, new Class[0]);
                mGetVolumeStateMethod = mStorageManager.getClass().getMethod(METHOD_GET_VOLUME_STATE, new Class[]{String.class});
                mIsRemovableMethod = mStorageVolume.getMethod(METHOD_IS_REMOVABLE, new Class[0]);
                mGetPathMethod = mStorageVolume.getMethod(METHOD_GET_PATH, new Class[0]);

                mStorageVolumeList = (Object[]) mGetVolumeListMethod.invoke(mStorageManager, new Object[0]);

                boolean mIsRemovable = false;

                if (mStorageVolumeList != null && mStorageVolumeList.length > 0) {
                    int mStorageVolumeCount = mStorageVolumeList.length;

                    Log.i(TAG, "init() === > StorageVolume Count = " + mStorageVolumeCount);


                    mInternalPathList.clear();
                    mExternalPathList.clear();

                    for (int i = 0; i < mStorageVolumeCount; ++i) {
                        String mStoragePath = (String) mGetPathMethod.invoke(mStorageVolumeList[i], new Object[0]);
                        mIsRemovable = ((Boolean) mIsRemovableMethod.invoke(mStorageVolumeList[i], new Object[0])).booleanValue();
                        if (!TextUtils.isEmpty(mStoragePath)) {
                            String state = (String) mGetVolumeStateMethod.invoke(mStorageManager, new Object[]{mStoragePath});
                            if ((state != null) && (state.equals(MOUNTED))) {
                                if (mIsRemovable) {
                                    Log.i(TAG, "init() === > external storage path = (" + mStoragePath + ")");
                                    mExternalPathList.add(mStoragePath);
                                } else {
                                    Log.i(TAG, "init() === > internal storage path = (" + mStoragePath + ")");
                                    mInternalPathList.add(mStoragePath);
                                }
                            }
                        }
                    }
                }
            } catch (ClassNotFoundException e) {
                handleInvalid();
                Log.e(TAG, "init() === > Exception:ClassNotFoundException");
            } catch (NoSuchMethodException e) {
                handleInvalid();
                Log.e(TAG, "init() === > Exception:NoSuchMethodException");
            } catch (IllegalArgumentException e) {
                handleInvalid();
                Log.e(TAG, "init() === > Exception:IllegalArgumentException");
            } catch (IllegalAccessException e) {
                handleInvalid();
                Log.e(TAG, "init() === > Exception:IllegalAccessException");
            } catch (InvocationTargetException e) {
                handleInvalid();
                Log.e(TAG, "init() === > Exception:InvocationTargetException");
            }
        } else {
            handleInvalid();
            Log.e(TAG, "init() === > can't get storage manager");
        }
        initSDCardPath();
    }

    private void handleInvalid() {
        mInternalPathList.add(Environment.getExternalStorageDirectory().getPath());
    }

    private void initSDCardPath() {
        if (!mExternalPathList.isEmpty()) {
            mSDCardPath = mExternalPathList.peek();
        } else if (!mInternalPathList.isEmpty()) {
            mSDCardPath = mInternalPathList.peek();
        } else {
            mSDCardPath = Environment.getExternalStorageDirectory().getPath();
        }
        Log.i(TAG, "initSDCardPath() === > SDCARD PATH = (" + mSDCardPath + ")");
    }


    /**
     * SDCARD是否存
     */
    public static boolean externalMemoryAvailable() {
        return android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取手机内部剩余存储空间
     *
     * @return
     */
    public static long getAvailableInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return availableBlocks * blockSize;
    }

    /**
     * 获取手机内部总的存储空间
     *
     * @return
     */
    public static long getTotalInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return totalBlocks * blockSize;
    }

    /**
     * 获取手机内置存储剩余存储空间
     *
     * @return
     */
    public static long getAvailableInternalSystemMemorySize() {
        File path = Environment.getRootDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return availableBlocks * blockSize;
    }

    /**
     * 获取手机内置存储总的存储空间
     *
     * @return
     */
    public static long getTotalInternalSystemMemorySize() {
        File path = Environment.getRootDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return totalBlocks * blockSize;
    }

    /**
     * 获取SDCARD剩余存储空间
     *
     * @return
     */
    public static long getAvailableExternalMemorySize() {
        if (externalMemoryAvailable()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long availableBlocks = stat.getAvailableBlocks();
            return availableBlocks * blockSize;
        } else {
            return ERROR;
        }
    }

    /**
     * 获取SDCARD总的存储空间
     *
     * @return
     */
    public static long getTotalExternalMemorySize() {
        if (externalMemoryAvailable()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long totalBlocks = stat.getBlockCount();
            return totalBlocks * blockSize;
        } else {
            return ERROR;
        }
    }

    public static long getAvailableMemorySize(String path) {
        if (null == path)
            return 0;
        StatFs stat = new StatFs(path);
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return availableBlocks * blockSize;
    }


}