LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := fract
LOCAL_SRC_FILES := fract.c

include $(BUILD_SHARED_LIBRARY)
