import React from 'react';
import { motion, AnimatePresence } from 'motion/react';
import { ShieldAlert, CheckCircle, XCircle } from 'lucide-react';

interface PermissionPopupProps {
  status: 'idle' | 'requesting' | 'granted' | 'denied';
  onGrant: () => void;
  onDeny: () => void;
  onAcknowledgeResult: () => void;
}

export function PermissionPopup({ status, onGrant, onDeny, onAcknowledgeResult }: PermissionPopupProps) {
  if (status === 'idle') return null;

  return (
    <AnimatePresence>
      <div className="fixed inset-0 z-50 flex items-center justify-center bg-black/50 backdrop-blur-sm px-4">
        <motion.div
          initial={{ opacity: 0, scale: 0.95 }}
          animate={{ opacity: 1, scale: 1 }}
          exit={{ opacity: 0, scale: 0.95 }}
          className="bg-white rounded-2xl shadow-xl p-6 w-full max-w-sm"
        >
          {status === 'requesting' && (
            <div className="text-center">
              <div className="mx-auto bg-amber-100 text-amber-600 w-16 h-16 rounded-full flex items-center justify-center mb-4">
                <ShieldAlert size={32} />
              </div>
              <h3 className="text-xl font-bold text-gray-900 mb-2">Yêu cầu cấp quyền</h3>
              <p className="text-gray-600 mb-6 text-sm">
                Ứng dụng cần quyền Hiển thị trên ứng dụng khác (Draw over other apps) và Accessibility để sử dụng chức năng RPA.
              </p>
              <div className="flex gap-3">
                <button
                  onClick={onDeny}
                  className="flex-1 px-4 py-2 bg-gray-100 text-gray-700 rounded-lg font-medium hover:bg-gray-200 transition-colors"
                >
                  Từ chối
                </button>
                <button
                  onClick={onGrant}
                  className="flex-1 px-4 py-2 bg-blue-600 text-white rounded-lg font-medium hover:bg-blue-700 transition-colors"
                >
                  Cấp quyền
                </button>
              </div>
            </div>
          )}

          {(status === 'granted' || status === 'denied') && (
            <div className="text-center">
              <div className={`mx-auto w-16 h-16 rounded-full flex items-center justify-center mb-4 ${status === 'granted' ? 'bg-green-100 text-green-600' : 'bg-red-100 text-red-600'}`}>
                {status === 'granted' ? <CheckCircle size={32} /> : <XCircle size={32} />}
              </div>
              <h3 className="text-xl font-bold text-gray-900 mb-2">
                {status === 'granted' ? 'Cấp quyền thành công' : 'Từ chối cấp quyền'}
              </h3>
              <p className="text-gray-600 mb-6 text-sm">
                {status === 'granted' 
                  ? 'Hệ thống đã ghi nhận quyền Accessibility và Overlay. Bạn có thể sử dụng RPA ngay bây giờ.' 
                  : 'Một số tính năng RPA sẽ không hoạt động vì thiếu quyền hệ thống.'}
              </p>
              <button
                onClick={onAcknowledgeResult}
                className="w-full px-4 py-2 bg-blue-600 text-white rounded-lg font-medium hover:bg-blue-700 transition-colors"
              >
                OK
              </button>
            </div>
          )}
        </motion.div>
      </div>
    </AnimatePresence>
  );
}
