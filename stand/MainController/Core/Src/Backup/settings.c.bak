#include "settings.h"

Settings_t settings = {0};
uint8_t initialized = 0;

void SetDefaults(){
	initialized = 1;
	settings.generalSettings.standId = STAND_ID;
	settings.signal.amplitude = AMPLITUDE;
	settings.signal.offset = OFFSET;
	settings.signal.form = CONST;
	settings.signal.frequency = FREQUENCY;
	settings.signal.outConfig = OUT_OFF;
	for (uint16_t i = 0; i < 512; i++)
		settings.signal.signalBuffer[0] = 0;
}

void InitSettings(){
	if (initialized == 0)
		SetDefaults();
}