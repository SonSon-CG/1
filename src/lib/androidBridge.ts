export interface AndroidBridge {
  startRecording: (macroId?: string) => void;
  stopRecording: () => void;
  startPlaying: (macroId?: string, rpaEnabled?: boolean, formDataStr?: string) => void;
  stopPlaying: () => void;
  pauseAction: () => void;
  saveConfig: (dataStr: string) => void;
  showToast: (msg: string) => void;
}

// Khai báo kiểu cho window để TypeScript không báo lỗi khi gọi window.Android
declare global {
  interface Window {
    Android?: AndroidBridge;
  }
}

/**
 * Gọi xuống Android Native nếu đang chạy trong WebView,
 * Nếu chạy trên web duyệt bình thường thì log ra console để debug.
 */
export const androidBridge = {
  startRecording: (macroId: string = 'general') => {
    if (window.Android && window.Android.startRecording) {
      window.Android.startRecording(macroId);
    } else {
      console.log(`[Android Bridge] Bắt đầu REC macro: ${macroId}`);
    }
  },
  
  stopRecording: () => {
    if (window.Android && window.Android.stopRecording) {
      window.Android.stopRecording();
    } else {
      console.log(`[Android Bridge] Stop REC`);
    }
  },

  startPlaying: (macroId: string = 'general', rpaEnabled: boolean = true, formData: any = {}) => {
    const dataStr = JSON.stringify({ macroId, rpaEnabled, formData });
    if (window.Android && window.Android.startPlaying) {
      window.Android.startPlaying(macroId, rpaEnabled, dataStr);
    } else {
      console.log(`[Android Bridge] Bỏ lỡ PLAY macro: ${macroId}, rpa: ${rpaEnabled}, data:`, formData);
    }
  },

  stopPlaying: () => {
    if (window.Android && window.Android.stopPlaying) {
      window.Android.stopPlaying();
    } else {
      console.log(`[Android Bridge] Stop PLAY`);
    }
  },

  pauseAction: () => {
    if (window.Android && window.Android.pauseAction) {
      window.Android.pauseAction();
    } else {
      console.log(`[Android Bridge] Pause Action`);
    }
  },

  saveConfig: (config: any) => {
    const dataStr = JSON.stringify(config);
    if (window.Android && window.Android.saveConfig) {
      window.Android.saveConfig(dataStr);
    } else {
      console.log(`[Android Bridge] Save Config:`, config);
    }
  }
};
