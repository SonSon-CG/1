import React, { createContext, useContext, useState, useEffect } from 'react';

export interface FormData {
  serverIp: string;
  serverPort: string;
  username: string;
  password: string;
  accountName: string;
  accountPass: string;
  phone: string;
  fullName: string;
}

export interface MacroConfig {
  rpaEnabled: boolean;
}

export interface RecordedAction {
  id: string;
  x: number;
  y: number;
  timestamp: number;
}

interface AppContextType {
  isPlaying: boolean;
  setIsPlaying: (val: boolean) => void;
  isRecording: boolean;
  setIsRecording: (val: boolean) => void;
  activeMacro: string | null;
  setActiveMacro: (id: string | null) => void;
  formData: FormData;
  updateFormData: (field: keyof FormData, value: string) => void;
  macroConfigs: Record<string, MacroConfig>;
  updateMacroConfig: (id: string, config: Partial<MacroConfig>) => void;
  recordedActions: RecordedAction[];
  addRecordedAction: (action: Omit<RecordedAction, 'id'>) => void;
  clearRecordedActions: () => void;
}

const defaultFormData: FormData = {
  serverIp: '', serverPort: '', username: '', password: '',
  accountName: '', accountPass: '', phone: '', fullName: ''
};

const LOCAL_STORAGE_KEY = 'rpa_form_data';

const AppContext = createContext<AppContextType | undefined>(undefined);

export function AppProvider({ children }: { children: React.ReactNode }) {
  const [isPlaying, setIsPlaying] = useState(false);
  const [isRecording, setIsRecording] = useState(false);
  const [activeMacro, setActiveMacro] = useState<string | null>(null);
  
  const [formData, setFormData] = useState<FormData>(() => {
    try {
      const saved = localStorage.getItem(LOCAL_STORAGE_KEY);
      return saved ? JSON.parse(saved) : defaultFormData;
    } catch {
      return defaultFormData;
    }
  });

  const [macroConfigs, setMacroConfigs] = useState<Record<string, MacroConfig>>({});
  const [recordedActions, setRecordedActions] = useState<RecordedAction[]>([]);

  useEffect(() => {
    localStorage.setItem(LOCAL_STORAGE_KEY, JSON.stringify(formData));
  }, [formData]);

  const updateFormData = (field: keyof FormData, value: string) => {
    setFormData(prev => ({ ...prev, [field]: value }));
  };

  const updateMacroConfig = (id: string, config: Partial<MacroConfig>) => {
    setMacroConfigs(prev => ({
      ...prev,
      [id]: { ...(prev[id] || { rpaEnabled: true }), ...config }
    }));
  };

  const addRecordedAction = (action: Omit<RecordedAction, 'id'>) => {
    setRecordedActions(prev => [...prev, { ...action, id: Math.random().toString(36).substring(2, 9) }]);
  };

  const clearRecordedActions = () => {
    setRecordedActions([]);
  };

  return (
    <AppContext.Provider value={{
      isPlaying, setIsPlaying,
      isRecording, setIsRecording,
      activeMacro, setActiveMacro,
      formData, updateFormData,
      macroConfigs, updateMacroConfig,
      recordedActions, addRecordedAction, clearRecordedActions
    }}>
      {children}
    </AppContext.Provider>
  );
}

export const useAppContext = () => {
  const context = useContext(AppContext);
  if (!context) throw new Error("useAppContext must be used within AppProvider");
  return context;
};
