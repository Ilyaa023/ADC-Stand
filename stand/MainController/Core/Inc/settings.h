#include "stm32f1xx_hal.h"
#include <arm_math.h>
#include <math.h>
#include "signal.h"

#define STAND_ID		10
#define AMPLITUDE		1024
#define OFFSET			0
#define FREQUENCY               500

typedef __packed struct {
	float32_t measuredOutAnalog;
        uint8_t inExMesMode;
	float32_t settedOutAnalog;
        uint8_t inExRefMode;
        uint16_t setRefPwm;
} VoltageModule_t;

typedef __packed struct {
	uint8_t standId;
	uint8_t subscribed;
} GeneralSettings_t;

typedef __packed struct {
	VoltageModule_t voltageModule;
	Signal_t signal;
	GeneralSettings_t generalSettings;
} Settings_t;

void InitSettings();