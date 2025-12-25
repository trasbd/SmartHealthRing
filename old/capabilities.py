class DeviceSupportCapabilities:

    DEVICE_SUPPORT_FLAGS = {
        # Byte 0 = f9
        0: [
            ("HAS_STEP_COUNT", 7),
            ("HAS_SLEEP", 6),
            ("HAS_REALTIME_DATA", 5),
            ("HAS_FIRMWARE_UPDATE", 4),
            ("HAS_HEART_RATE", 3),
            ("HAS_INFORMATION", 2),
            ("HAS_MULTI_LANGUAGE", 1),
            ("HAS_BLOOD", 0),
        ],
        # Byte 1 = 09
        1: [
            ("HAS_HEART_ALARM", 7),
            ("HAS_BLOOD_ALARM", 6),
            ("HAS_ECG_REAL_UPLOAD", 5),
            ("HAS_ECG_HISTORY_UPLOAD", 4),
            ("HAS_BLOOD_OXYGEN", 3),
            ("HAS_RESPIRATION_RATE", 2),
            ("HAS_HRV", 1),
            ("HAS_MORE_SPORTS", 0),
        ],
        # Byte 6 = 0c
        6: [
            ("HAS_LONG_SITTING", 7),
            ("HAS_ANTI_LOST", 6),
            ("HAS_FIND_PHONE", 5),
            ("HAS_FIND_DEVICE", 4),
            ("HAS_FACTORY_SETTING", 3),
            ("HAS_BLOOD_LEVEL", 2),
            ("HAS_NOTIFICATION_TOGGLE", 1),
            ("HAS_LIFT_BRIGHT", 0),
        ],
        # Byte 7 = d8
        7: [
            ("HAS_SKIN_COLOR", 7),
            ("HAS_WECHAT_SPORT", 6),
            ("HAS_SEARCH_AROUND", 5),
            ("HAS_WEATHER_TODAY", 4),
            ("HAS_WEATHER_TOMORROW", 3),
            ("HAS_ECG_DIAGNOSIS", 2),
            ("HAS_PHONE_SUPPORT", 1),
            ("HAS_ENCRYPTION", 0),
        ],
        # Byte 8 = 10
        8: [
            ("HAS_TEMP_ALARM", 7),
            ("HAS_TEMP_AXILLARY", 6),
            ("HAS_CVRR", 5),
            ("HAS_BP_CALIBRATION", 4),
            ("HAS_ECG_RIGHT_ELECTRODE", 3),
            ("HAS_THEME", 2),
            ("HAS_MUSIC", 1),
            ("HAS_TEMP", 0),
        ],
        # Byte 9 = 04
        9: [
            ("HAS_INACCURATE_ECG", 7),
            ("HAS_CONTACTS", 6),
            ("HAS_DIAL", 5),
            ("HAS_FEMALE_CYCLE", 4),
            ("HAS_SHAKE_TAKE_PHOTO", 3),
            ("HAS_MANUAL_TAKE_PHOTO", 2),
            ("HAS_SET_INFO", 1),
            ("HAS_TEMP_CALIBRATION", 0),
        ],
        # Byte 10 = 01
        10: [
            ("HAS_REALTIME_MONITOR_MODE", 7),
            ("HAS_INDOOR_WALKING", 6),
            ("HAS_OUTDOOR_WALKING", 5),
            ("HAS_INDOOR_RUNNING", 4),
            ("HAS_OUTDOOR_RUNNING", 3),
            ("HAS_PINGPONG", 2),
            ("HAS_FOOTBALL", 1),
            ("HAS_MOUNTAIN_CLIMBING", 0),
        ],
        # Byte 11 = b2
        11: [
            ("HAS_RUNNING", 7),
            ("HAS_FITNESS", 6),
            ("HAS_RIDING", 5),
            ("HAS_ROPE_SKIPPING", 4),
            ("HAS_BASKETBALL", 3),
            ("HAS_SWIMMING", 2),
            ("HAS_WALKING", 1),
            ("HAS_BADMINTON", 0),
        ],
        # Byte 12 = b6
        12: [
            ("HAS_YOGA", 6),
            ("HAS_WEIGHT_TRAINING", 5),
            ("HAS_JUMPING", 4),
            ("HAS_SITUPS", 3),
            ("HAS_ROWING_MACHINE", 2),
            ("HAS_STEPPER", 1),
            ("HAS_INDOOR_RIDING", 0),
        ],
        # Byte 14 = 40
        14: [
            ("HAS_REAL_EXERCISE_DATA", 0),  # bit0
            ("HAS_TEST_HEART", 1),
            ("HAS_TEST_BLOOD", 2),
            ("HAS_TEST_SPO2", 3),
            ("HAS_TEST_TEMP", 4),
            ("HAS_TEST_RESP_RATE", 5),
            ("HAS_INFO_PUSH", 6),
            ("HAS_CUSTOM_DIAL", 7),
        ],
        # Byte 15 = 0f
        15: [
            ("HAS_INFLATED", 0),
            ("HAS_SOS", 1),
            ("HAS_BLOOD_OXYGEN_ALARM", 2),
            ("HAS_UPLOAD_INFLATE_BLOOD", 3),
            ("HAS_VIBER_NOTIFY", 4),
            ("HAS_OTHER_NOTIFY", 5),
            ("HAS_FLIP_DIAL_IMAGE", 6),
            ("HAS_SCREEN_BRIGHTNESS", 7),
        ],
        # Byte 17 = 14
        17: [
            ("HAS_VIBRATION_INTENSITY", 0),
            ("HAS_SET_SCREEN_TIME", 1),
            ("HAS_SCREEN_BRIGHTNESS2", 2),
            ("HAS_BLOOD_SUGAR", 3),
            ("HAS_PAUSE_EXERCISE", 4),
            ("HAS_DRINK_WATER_REMINDER", 5),
            ("HAS_BUSINESS_CARD", 6),
            ("HAS_URIC_ACID", 7),
        ],
        # Byte 18 = 50
        18: [
            ("HAS_VOLLEYBALL", 0),
            ("HAS_KAYAK", 1),
            ("HAS_ROLLERSKATING", 2),
            ("HAS_TENNIS", 3),
            ("HAS_GOLF", 4),
            ("HAS_ELLIPTICAL", 5),
            ("HAS_DANCE", 6),
            ("HAS_ROCK_CLIMBING", 7),
        ],
        # Byte 22 = 20
        22: [
            ("HAS_SLEEP_REMIND", 0),
            ("HAS_DEVICE_SPEC", 1),
            ("HAS_LOCAL_SPORT_DATA", 2),
            ("HAS_LOGO", 3),
            ("HAS_MOTION_DELAY_DISCONNECT", 4),
            ("HAS_BATTERY_INFO_UPLOAD", 5),
            ("HAS_PRESSURE", 6),
            ("HAS_MAX_OXYGEN_INTAKE", 7),
        ],
    }

    @classmethod
    def decode(cls, bArr: bytes) -> dict:
        result = {}

        for byte_index, entries in cls.DEVICE_SUPPORT_FLAGS.items():
            if byte_index >= len(bArr):
                break

            byte_val = bArr[byte_index]

            for name, bit in entries:
                if bit == "byte":
                    result[name] = byte_val
                else:
                    result[name] = ((byte_val >> bit) & 1) == 1

        return result
