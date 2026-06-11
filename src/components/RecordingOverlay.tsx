import React from 'react';
import { motion, AnimatePresence } from 'motion/react';
import { useAppContext } from '../context/AppContext';

export function RecordingOverlay() {
  const { isRecording } = useAppContext();

  return (
    <AnimatePresence>
      {isRecording && (
        <motion.div
          initial={{ opacity: 0 }}
          animate={{ opacity: 1 }}
          exit={{ opacity: 0 }}
          className="fixed inset-0 z-20 pointer-events-none border-[6px] border-red-500/50"
        >
          {/* Top right recording indicator */}
          <div className="absolute top-4 right-4 flex items-center gap-2 bg-red-500/80 backdrop-blur text-white px-3 py-1.5 rounded-full text-xs font-bold font-mono shadow-lg">
            <span className="w-2.5 h-2.5 bg-white rounded-full animate-pulse"></span>
            REC
          </div>
          
          {/* Subtle scanning grid line simulation */}
          <motion.div 
            initial={{ top: "0%" }}
            animate={{ top: "100%" }}
            transition={{ duration: 3, repeat: Infinity, ease: "linear" }}
            className="absolute left-0 right-0 h-[2px] bg-red-500/30"
          />
        </motion.div>
      )}
    </AnimatePresence>
  );
}
